# class
> Scalaにおけるクラスはオブジェクトを作るための設計図です。 クラスはメソッド、値、変数、型、オブジェクト、トレイト、クラスを持ち、それらはまとめて メンバー と呼ばれます。
全てのクラスは、JavaのObjectクラスを暗黙的に継承している。  
メンバーとしての要素をまとめるだけではなく、以下の機能もある。  
- ユーザー独自の型を作成
- スーパークラスになれる


## クラスを定義する
```
//例1：コンストラクターなし
class User 

//例2：コンストラクターあり
class User(val name: String, val age: Int)

//例3：デフォルト値の入ったコンストラクター
class User(var name: String = "No Name", var age: Int = 20)
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

## クラスパラメータのスコープ
val や var が存在しないパラメーターはクラス内でだけで参照できるプライベートな値や変数となる。
```
//クラスを定義、インスタンス化
class Point(x: Int, y: Int)
val point = new Point(1, 2)

//アクセスする
point.x  // error: value x is not a member of Point
```

プライマリコンストラクタのval と var を持つパラメーターはパブリックになります。 しかしながらval は不変となるため、以下のように記述することはできません。
```
//クラス定義、インスタンス化
class Point(val x: Int, val y: Int)
val point = new Point(1, 2)

//値にアクセス
scala> Point.x
val res0: Int = 1

//値を上書き
point.x = 3  // error: reassignment to val
```



## 参考
[クラス](https://docs.scala-lang.org/ja/tour/classes.html)
