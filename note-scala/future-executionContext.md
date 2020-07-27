## ExecutionContext
Futureを使うときに、合わせてimportするが、闇雲にimportしてはいけないので注意。  
```
//非推奨：scalaが標準で提供しているExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global


//推奨：implicit ec: ExecutionContextをメゾットまたはコンストラクタのクラスパラメータに追加する。
@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc){
```

ExecutionContextは、２つの抽象メゾットをもつ。  
```
//非同期処理の実行
def execute(runnable: Runnable): Unit

//エラー処理
def reportFailure(@deprecatedName('t) cause: Throwable): Unit
```

Futureに定義されている関数は、実際に非同期処理の実行（excute）とエラー処理（reportFailure）を、暗黙のパラメータとして渡されたExecutionContext型の値に委譲している。  
つまり、Futureで定義された関数は、ExecutionContextの値の実装によって、どのようにexcuteまたはreportFailureされるかが決定される。  


### ExecutionContextの動きのイメージ
![例１](https://user-images.githubusercontent.com/57429437/81898909-95712300-95f4-11ea-8419-ea128c1b3cec.png)

> 例えば10個の重いタスクがあったとして、それぞれのタスクに対し new Thread & start (スレッド立ち上げ) して処理した場合は当然次のように10個のスレッドが立ち上がる。 
> しかし、この実装は以下のような問題点がある
>
> ・タスク数分スレッドを立ち上げるためメモリ/CPU資源の無駄になる 
> ・しかも暇しているスレッドがいる

![例２](https://mashi.hatenablog.com/entry/2014/11/24/010417)
> このようにタスク(Runnableといったほうが適切か)を「いい感じ((ExecutionContextの実装による)」にスレッドに分配するのがExecutionContextの役目だ。 
> 一般には「一定数を最大数とするスレッドプールを持っており、空いてるスレッドを利用してRunnableを処理してくれる」と考えればいいのではないだろうか。


ExecutionContextは、executeメソッドに渡される[Runnable](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html)を実行するときに非同期でなければいけない。  
特別な場合に同期処理としてExecutionContextが実行される場合もあるが、それは明示的かつExecutionContextの同期的実行が安全でなければいけない。  

Future.onCompleteなどのAPIには、コールバックと暗黙的なExexutionContextが必要になる。暗黙的なExecutionContextは、コールバックの実行のために必要となる。  

実装者は、どの部分にexecutionポリシーを適用したいか、慎重に考えなけらばいけない。
1アプリケーションにつき１箇所、もしくは論理的に関連している１コードセクションに１つが理想。　　

推奨方法としては、`implicit ec: ExecutionContext`をメゾットかクラスコンストラクターパラメータに追加すること。  

追加する例：
```
@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents
)(implicit ec: ExecutionContext) <- ココ
  extends MessagesAbstractController(cc){
```
