# class
- オブジェクトを作るための設計図  
- メンバー（メソッド、値、変数、型、オブジェクト、トレイト、クラス）を持つことができる  
- ユーザー独自の型を作成
- スーパークラスになれる

## クラスの用語
```
class Point(var x: Int, y: Int) {

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString: String =
    s"($x, $y)"
}
```
コンストラクター：`class Point(var x: Int, var y: Int)`  
メンバー：`x`, `y`, `move`, `toString`  
フィールド：`var x`  
クラスパラメータ：`y`（valやvarがない）　　
メゾット：`move`, `toString`  


## クラスを定義する
```
//例1：コンストラクタなし
class User 

//例2：コンストラクターあり
class User(val name: String, val age: Int)

//例3：クラスパラメータ
class User(name: String, age: Int)

//例3：デフォルト値の入ったコンストラクター
class User(var name: String = "No Name", var age: Int = 20)
```

### フィールドとクラスパラメータの違い
フィールド：`val` / `var`を付けて宣言。コンストラクタの外からも参照できる。  
クラスパラメータ：`var` / `val`を付けて宣言。コンストラクタ内でしか参照できない。  

```
//クラスパラメータ
class Point(x: Int, y: Int)
val point = new Point(1, 2)

point.x  // error: value x is not a member of Point
```
```
//フィールド
class Point(val x: Int, val y: Int)
val point = new Point(1, 2)

point.y //2
```

## クラスを使う
例1：コンストラクターありのクラス
```
//クラスを定義
scala> class User(val name: String, val age: Int)
class User

//インスタンス化
scala> val user1 = new User("Takashi", 20)
val user1: User = User@3ee1b4b2

//値を取り出す
scala> user1.name
val res0: String = Takashi
```

例2：クラスパラメータをメゾットで使用する
```
class User(val name: String, val age: Int) {
  //代入値の省略(例えば、(name, age))はできない
  def greet(_name: String = name , _age: Int = age): Unit = {
    println(s"Hello, I am ${name} and ${age} years old}")

}

val user1 = new User("Tom", 20)
user1.greet()
//Hello, I am Tom and 20 years old
```

//例3：インスタンスを引数に代入して使う
```
class Writer(val firstname: String, val surname: String, val year: Int) {
  def fullname(firstname: String = firstname, surname: String = surname): String = {
    firstname + " " + surname
  }
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
  def authorAge(): Int = {
    yearOfRelease - author.year
  }
  def isWrittenBy(): String = {
    author.fullname()
  }
}

def main(args: Array[String]): Unit = {
  val author = new Writer("J.K", "Rolling", 1965)
  println(author.fullname())

  val novel = new Novel("Harry Potter", 1977, author)
  println(novel.authorAge)
  println(novel.isWrittenBy)
}
```



## 参考
[クラス](https://docs.scala-lang.org/ja/tour/classes.html)
