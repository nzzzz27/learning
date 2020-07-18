# Option[+A]型
値がない場合を示す時に使用する。  
[+A]は型パラメータで、中に入る値の型を示す。  

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

## メゾット - 存在確認
### `.isDefined`
Someならtrue, Noneならfalseを返す。  
```
//定義
def isDefined: Boolean = !isEmpty
```

サンプルコード
```
//Someのとき
scala> Option(100).isDefined
val res3: Boolean = true

//Noneのとき
scala> None.isDefined
val res4: Boolean = false
```

### `.isEmpty`
戻り値：Noneならtrue、Someならfalse
```
//定義
def isEmpty: Boolean
```

### `.nonEmpty`
Someならtrue

### `orElse()`
戻り値：Noneなら指定したデフォルトのOption型、SomeならSomeを返す。　　
```
//Someのとき
scala> Some(1).orElse(Option(0))
val res9: Option[Int] = Some(1)

//Noneのとき
scala> None.orElse(Option(0))
val res11: Option[Int] = Some(0)
```

### `.isExist`
戻り値：Someならtrue、Noneならfalseを返す。  
```
//定義
@inline final def exists(p: A => Boolean): Boolean =
    !isEmpty && p(this.get)
```
### `.contains()`
戻り値：引数の値が含まれていたらtrue、なければfalseを返す。
```
//一致する時
scala> Some("abc").contains("abc")
val res25: Boolean = true

//一致しない時
scala> Some("abc").contains("b")
val res24: Boolean = false
```

## メゾット - 値取得
### `.get`
戻り値：Someの値を返す。Noneの時は例外が発生する。  
```
//定義
def get: A
```
```
//Someのとき
scala> Some(100).get
val res19: Int = 100

//Noneの時
scala> None.get
java.util.NoSuchElementException: None.get
  at scala.None$.get(Option.scala:627)
  ... 40 elided
```

### `.getOrElse()`
戻り値：Noneなら指定したデフォルトの値、SomeならそのままSomeの値を返す。
```
//定義
 @inline final def getOrElse[B >: A](default: => B): B =
    if (isEmpty) default else this.get
```

```
//Someのとき
scala> Some("hello").getOrElse("bye")
val res13: String = hello

//Noneのとき
scala> None.getOrElse(0)
val res14: Int = 0

//空文字はSome扱い
scala> Some(" ").getOrElse("bye")
val res18: String = " "
```

## メゾット - 値加工
### `.map(式)`
戻り値：Option型の加工された値  
`.map()`メゾット内の第一引数には、Option型の値が入っていて、それを式で加工した後にOption型に入れ直して返却している。
```
//定義
@inline final def map[B](f: A => B): Option[B] =
    if (isEmpty) None else Some(f(this.get))
```
```
//例1
scala> Some(10).map(i => i * 10)
val res0: Option[Int] = Some(100)

//例2
scala> Some("Tom").map(_ + "さん")
val res1: Option[String] = Some(Tomさん)

//例3: StringをIntで加工
scala> Some("Hi": String).map(i => i * 2)
val res8: Option[String] = Some(HiHi)
```

### `.flatten`
戻り値：Some() またはNone  
`Some(Some("abc"))`のように、`Option[Option[A]]`と入れ子になったOption型を1階層にする。　　
```
//定義
def flatten[B](implicit ev: A <:< Option[B]): Option[B] =
    if (isEmpty) None else ev(this.get)
```
```
//例1
scala> Some(Some("abc")).flatten
val res13: Option[String] = Some(abc)

//例2
scala> Some(None).flatten
val res14: Option[Nothing] = None

//例3：入れ子になっていないSome()だとエラー
scala> Some(100).flatten
                 ^
       error: Cannot prove that Int <:< Option[B].

//例4：Noneではエラーにならない
scala> None.flatten
val res16: Option[Nothing] = None
```

### `.flatMap()`
戻り値：Soeme()またはNone  
`.flatten`と`.map()`の組み合わせ。Option型の値を加工して返却する。　　
```
//定義
@inline final def flatMap[B](f: A => Option[B]): Option[B] =
    if (isEmpty) None else f(this.get)
```
```
//例1
scala> Some(Some(10)).flatMap(i => i)
val res18: Option[Int] = Some(10)

//例2
scala> Some(Some(10)).flatMap(i => i.map(n => n * 2))
val res21: Option[Int] = Some(20)

//例3：入れ子になっていないSomeだと、type mismatchになる
scala> Some(100).flatMap(i => i * 2)
                                ^
       error: type mismatch;
        found   : Int
        required: Option[?]
```

## メゾット - 型変換
### `.toList`
戻り値：List型に変換
```
//Someのとき
scala> Some("abc").toList
val res26: List[String] = List(abc)

//Noneのとき
scala> None.toList
val res27: List[Nothing] = List()
```

## 参考文献
[object Option](https://github.coam/scala/scala/blob/2.12.x/src/library/scala/Option.scala)
[富永さんメモ](https://github.com/takapi327/learners-dictionary/blob/master/scala/summary/Option.md)  
[Scala Option](http://www.mwsoft.jp/programming/scala/scala_option.html)

