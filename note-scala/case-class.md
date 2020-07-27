# case class
- 便利なメソッドやコンパニオンオブジェクト(クラスと同名のオブジェクト)が自動的に生成され使えるようになる
- 独自の型が持てる
- 不変なデータを作るのに適しています
- インスタンス化は不要（applyメゾットを標準保有しているため）
- フィールドは、自動的に`val`として宣言される
- classを継承できる
- abstractなどの修飾子は使えないし、継承もできない

## 使い方
定義
```
//パラメータは、自動的にval（定数）として宣言される
//パラメータの上書きにはcopyメゾットが必要

case class クラス名(
  パラメータ名: 型
)

/*
 case class クラス名(
  フィールド/コンストラクタ
 ) 
*/
```

定義の例
```
//example1
case class Person(
  name: String, 
  age: Int
)
defined class Person

scala> val person = Person("Tanaka", 20)
person: Person = Person(Tanaka,20)


//example2
//デフォルト値の設定もできる
case class Person2(
  name: String = "Tanaka", 
  age : Int = 20
)
defined class Person

scala> val person2 = Person2()
person: Person2 = Person2(Tanaka,20)
```


パラメータ取得
```
scala> person.name
res0: String = Tanaka

scala> person.age
res1: Int = 20
```

## 自動生成される便利メゾットたち
- apply()
  - インスタンスの生成に暗黙的に使われている

- unapply()
  - return value: Option[A] / Option[(A, B)] / true
  - パラメータを取り出し、別のインスタンスを生成することのできるメソッド
```
//example1
  case class Person(
    name: String,
    age: Int
  )
  defined class Person

  scala> val person = Person("Tanaka", 20)
  person: Person = Person(Tanaka,20)

  scala> Person.unapply(person)
  res2: Option[(String, Int)] = Some((Tanaka,20))

//example2 
  case class Person ()
  val person = Person()

  Person.unapply(person) 
  true
```

- copy()
  - 既存のインスタンスから、copyメソッド内で渡された値を変更した、新たなインスタンスを生成
  - 別のインスタンスを生成しているだけで、既存のインスタンスの値は変更しない
```
scala> val person1 = Person("Tanaka", 20)
person1: Person = Person(Tanaka,20)

scala> val person2 = person1.copy(name = "Sato")
person2: Person = Person(Sato,20)
```

- toString()
  - case classを綺麗に整形されたString型に変換
```
person.toString()
> Person(Tanaka,20)
```

- equals()
  - インスタンス同士を比較して、すべての値が同一であればtrueが返ります。
  - case classでは、値が同じ別インスタンスは、true(equals)
  - classでは、値が同じ別インスタンスは、false(not equals)
```
scala> case class Person(name: String, age: Int)
defined class Person

scala> val person1 = Person("Tanaka", 20)
person1: Person = Person(Tanaka,20)

scala> val person2 = Person("Tanaka", 20)
person2: Person = Person(Tanaka,20)

scala> person1.equals(person2)
res2: Boolean = true
```

- hashCode()
  - 各フィールドの内容を元にハッシュ値を算出


## クラスとの違い
class  
- 値が同じでも別インスタンスから、２つのインスタンスは`=`ではない。
- 同値性の変更には、javaのメゾットであるhashcodeとequalsをoverrideする必要がある。
- インスタンス化にはnewが必要

```
case class Person(
  name: String,
  age:  Int
)

val person = Person("Tanaka", 20)
```

case class
- 値が同じ別インスタンスは`=`になる。
- コンパニオンオブジェクトとしてapply()が標準定義されているので、newでインスタンスする必要がない



# 参考サイト
- https://docs.scala-lang.org/ja/tour/case-classes.html
- http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html
- https://qiita.com/4245Ryomt/items/ae1468e634523c83d571

