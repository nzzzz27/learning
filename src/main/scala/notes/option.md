# Option[+A]型
値がない場合を示す時に使用する。  
[+A]は型パラメータで、中に入る値の型を示す。  

Option[+A]型をコレクションやモナドのように扱う最も慣用的な方法は、`map`, `flatMap`, `filter`, `foreach`を使うこと。

## [定義](https://github.coam/scala/scala/blob/2.12.x/src/library/scala/Option.scala)
値があれば、Some[A]インスタンス、なければNoneオブジェクトを返す。
```
// Some[A]
def apply[A](x: A): Option[A] = if (x == null) None else Some(x)

// None 
def empty[A] : Option[A] = None
```

## Option型の値の作り方
```
// Some[A]になる場合1
scala> Option("hello")
val res0: Option[String] = Some(hello)

// Some[A]になる場合2
scala> Option.apply(100)
val res5: Option[Int] = Some(100)

// Noneになる場合1
scala> None
val res1: None.type = None

// Noneになる場合2
scala> Option(null)
val res2: Option[Null] = None
```

## メゾット

## 参考サイト 
