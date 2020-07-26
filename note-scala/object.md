# Object
Scalaでは全ての値がオブジェクト。すべてのメゾットは何らかのオブジェクトに所属している。  
また、**Scalaのオブジェクトは全てシングルトンオブジェクト**である。  

シングルトンオブジェクトとは、1つのインスタンスしか生成することができないオブジェクトのこと。  

```
//定義
object オブジェクト名 {
  val sample = "sample"
}

//値にアクセス
オブジェクト名.smaple  //sample
```


シングルトンオブジェクトのため、`new`を用いたインスタンス化はできない。  
```
//オブジェクトを定義
scala> object Animal {
     |  val spieces: String = "dog"
     |  def bark(): Unit = println("Bow wow")
     | }

//newでインスタンス化しようとするとエラーとなる
scala> new Animal
                      ^
       error: not found: type Animal

//正しい呼び出し方
scala> Animal
Animal.type = Animal$@1e7bf1d
```

## コンパニオンオブジェクト / コンパニオンクラス
- クラスと同じ名前、同じスコープを持つオブジェクトのこと
- 逆に、そのクラスは、オブジェクトのコンパニオンクラスと呼ばれる  
- コンパニオンクラスやコンパニオンオブジェクトは自身の**コンパニオンのプライベートメンバーにアクセスできる**  
- コンパニオンクラスのインスタンスに特定されないメソッドや値にはコンパニオンオブジェクトを使う  
- **シングルトンオブジェクトは、コンパニオンクラスによって独自の型を作ることができる**。

```
class PersonalInfo (
  val name: String,
  private val secret: String = "My secret is, I hate tomato" //プライベート
)

object PersonalInfo {
  val monica = new PersonalInfo("Monica")
  def showSecrets(): Unit = println(s"${monica.secret}") //プライベートにアクセスできる
}

//結果
PersonalInfo.showSecrets
```

## スタンドアロンオブジェクト
- コンパニオンクラスと同じ名前を共有しないシングルトンオブジェクトのこと。  
- 関連するユーティリティメゾットのコレクションを作るときや、Scalaアプリケーションのエントリーポイントを定義する時などに使われる。  

## 参照
[Udemy: 3-14 Scala Objects](https://www.udemy.com/course/rock-the-jvm-scala-for-beginners/learn/lecture/7660652#questions)  
[[Scala] シングルトンオブジェクトとコンパニオンオブジェクト](https://qiita.com/kingzer0314/items/a6b82034f760f024fdfa)  
コップ本 P.83~
