# 継承
似た構造や目的を持ったオブジェクトを一まとめにして、その機能や要素を他のクラスに継承させることができる。元（親）をスーパークラス、継承先（子）をサブクラスという。　　

継承することのメリットとしては、以下が挙げられる。　　
- 独自の型を作成し、複数の型を扱う（多態性） ※Seqなど要素の型が一律でないといけない時に便利
- 持つべき要素を強制的に共通化できる
- 実装の再利用（メゾットの上書き）

## 継承の方法
特徴
- [class](https://github.com/nzzzz27/scala-practice/blob/master/src/main/scala/notes/extension.md#class)
  - サブクラスを1つしか作成できない
  - 要件からメゾットまでフレキシブルなものに対して使用しやすい。例えば電子レンジのように、色、サイズ、W数など決まっていないもの。
- [case class](https://github.com/nzzzz27/scala-practice/blob/master/src/main/scala/notes/extension.md#case-class)
  - サブクラスを複数持てる
  - 便利メゾットが使えたり、newでインスタンス化する必要がない
- [trait](https://github.com/nzzzz27/scala-practice/blob/master/src/main/scala/notes/extension.md#trait)
  - サブクラスを複数持てる
  - 振る舞いや見た目など、規格が決まっているものに対して使用しやすい。例えば、USBのようにサイズや差し込み口の形が決定しているもの。


## class
定義方法
```
//スーパークラス
class クラス名(クラスパラメータ) { フィールド }

//サブクラス
class サブクラス名(サブクラスのコンストラクタ) extends スーパークラス名(スーパークラスのコンストラクタ) { フィールド }
```
例1：Shapeクラスを継承した、三角形の面積を求めるサブクラスを作る。
```
//スーパークラス
class Shape(val width: Int, val height: Int) {
  def showColor(): Unit = {
      println("これは、青色の図形です。")
    }
   def showShapeName(): Unit = {
     println("図形")
   } 
 }

//サブクラス
class Triangle(width: Int, height: Int) extends Shape(width, height) {
  //スーパークラスのメゾットを上書き
  override def showShapeName(): Unit = {
    println("三角形")
  }
  def getArea(): Int = {
    width * height / 2
  }
}

//インスタンス化
val triangle = new Triangle(10, 30)

//引数の値を取得
triangle.width //10

//.getArea()メゾットを実行
triangle.getArea()

//上書きされたメゾット
triangle.showShapeName()  //三角形

//スーパークラスのメゾット
triangle.draw()  //これは、青色の図形です。
```

## case class
### case classを定義する
```
case class クラス名(クラスパラメータ)
```

### case classを使う
例1：クラスにcase classを継承
```
//case classを定義
case class Shop(name: String, reccomend: String) {
  def greetings(): Unit = {
    println(s"$name へようこそ! おすすめは、$reccomend です！")
  }
}

//クラスに継承
class Cafe(name: String, reccomend: String) extends Shop (name, reccomend)

//インスタンス化
val cafe = new Cafe("Scala Cafe", "Coffee")

//greetingsメゾットを実行
cafe.greetings //Scala Cafe へようこそ! おすすめは、Coffee です！
```


## trait 
classから、newでインスタンス化する機能を省いたもの。インスタンス化ができないので、パラメータを持たない。  
クラスがトレイトを継承することをmixinという。

### traitを定義する
```
//宣言のみ
trait HairColor

//フィールドがある場合
trait Namable {
  val name: String
  def show(): Unit = println(name)
}
```

### traitを使う
トレイトを定義し、継承する
```
trait トレイト名 extends スーパートレイト1 with スーパートレイト2 { }
```

トレイトをミックスインする
```
class クラス名 extends スーパークラス with スーパートレイト { } 
```

例1：トレイトにトレイトを継承
```
//traitを定義
trait Job { val name: String }

//継承
val job1 = new { val name = "teacher" } with Job

//値を取得
job1.name //teacher
```

例2：mixin
```
//traitを定義
trait Namable {
  val name: String 
  def show(): Unit = println(name)
}

//mixin
class Employee(val name: String) extends AnyRef width Namable 

//実行
val taro = new Employee("太郎")
taro.display() //太郎
```










