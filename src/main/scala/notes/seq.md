# Seq型
> 列 (Seq) トレイトは、長さ (length) があり、それぞれの要素に 0 から数えられた固定された添字 (index) がある Iterable の一種だ。

## 演算 - 値取得
### `(i)`
戻り値：Seqの中の値  

```
scala> Seq(1, 2, 3, 4, 5)(0)
val res0: Int = 1
```

### `.head`
戻り値：Seqの初めの値。値がない時は例外が返ってくる。
```
//例1
scala> Seq(1, 2, 3).head
val res12: Int = 1

//例2
scala> Seq(Seq("Apple", "Orange"), Seq(1, 2, 3)).head
val res11: Seq[Any] = List(Apple, Orange)

//例3：値が一つもない時は例外が返ってくる
scala> Seq().head
java.util.NoSuchElementException: head of empty list
```

### `.headOption`
戻り値：Seqの初めの値をOption[A]型で返す。ない場合はNoneを返す。
```
//例1
scala> Seq(1, 2, 3).headOption
val res15: Option[Int] = Some(1)

//例2
scala> Seq().headOption
val res14: Option[Nothing] = None
```

### `.last`
戻り値：Seqの最後の値を返す。ない場合は例外を返す。
```
//例1
scala> Seq(1, 2, 3).last
val res19: Int = 3

//例2
scala> Seq().last
java.util.NoSuchElementException: last of empty list
```

### `.lastOption`
戻り値：Seqの最後の値をOption[A]型で返す。ない場合はNoneを返す。
```
//例1
scala> Seq(1, 2, 3).lastOption
val res16: Option[Int] = Some(3)

//例2
scala> Seq().lastOption
val res17: Option[Nothing] = None
```

### `.tail`
戻り値：Seqの先頭の値を除いたListを返す。値がない場合は例外を返す。
```
//例1
scala> Seq(1, 2, 3).tail
val res20: Seq[Int] = List(2, 3)

//例2
scala> Seq().tail
java.lang.UnsupportedOperationException: tail of empty list
```

### `.init`
戻り値：Seqの最後の値を除いたListを返す。値がない時は例外を返す。
```
//例1
scala> Seq(1, 2, 3).init
val res22: Seq[Int] = List(1, 2)

//例2
scala> Seq().init
java.lang.UnsupportedOperationException: init of empty list
```

## 演算 - 長さを取得
### `.length`
戻り値：Seqの要素数（Int）
```
scala> Seq(1, 2, 3).length
val res10: Int = 3
```

## 演算 - 存在確認
### `.exists(式)`
戻り値：一つでも条件を満たすものがあればtrue、なければfalseを返す。
```
scala> Seq(1, 2, 3).exists(i => i == 2)
val res6: Boolean = true
```

### `.contains()`
戻り値：引数で指定した値が一つでもあればtrue、なければfalseを返す。
```
scala> Seq("A", "B", "C").contains("A")
val res8: Boolean = true

scala> Seq("A", "B", "C").contains("a")
val res9: Boolean = false
```

### `.find(式)`
戻り値：条件に合致した初めの値をOption[A]型で返す。ない場合はNoneを返す。
```
scala> Seq(1, 2, 3).find(i => i % 2 == 0)
val res24: Option[Int] = Some(2)
```

### `.min`
戻り値：最小の値もしくは例外。　　　　
```
//例1：英字<日本語
scala> Seq("あ", "B").min
val res57: String = B

//例2：String, Intの混合は例外になる
scala> Seq(1, "あ", "B").min
error: No implicit Ordering defined for Any.
```

### `.max`
戻り値：最大の値もしくは例外
```
//例1：英字>日本語
scala> Seq("あ", "B").max
val res57: String = あ

//例2：String, Intの混合は例外になる
scala> Seq(1, "あ", "B").max
error: No implicit Ordering defined for Any.
```

## 演算 - 加算
### `+:`
左辺を右辺のコレクション先頭に追加して返却。　　
```
//例1
scala> "ABC" +: Seq("A", "B", "C")
val res38: Seq[String] = List(ABC, A, B, C)

//例2：空のコレクションにも追加できる
scala> "ABC" +: Seq()
val res39: Seq[String] = List(ABC)

//例3
scala> "ABC" +: Nil
val res42: List[String] = List(ABC)
```

### `:+`
右辺を、左辺のコレクションの末尾に追加
```
//例1
scala> Seq("A", "B", "C") :+ "ABC"
val res43: Seq[String] = List(A, B, C, ABC)

//例2
scala> Seq() :+ "ABC"
val res44: Seq[String] = List(ABC)

//例3
scala> Nil :+ "ABC"
val res45: List[String] = List(ABC)
```

### `++`
左右の辺のコレクションを連結
```
scala> Seq("A", "B", "C") ++ Seq("D", "E")
val res47: Seq[String] = List(A, B, C, D, E)
```

## 演算 - 加工
### `.map(式)`
戻り値：式を適用した値をListに入れて返す。
```
//例1
scala> Seq(1, 2, 3, 4, 5).map(i => i * 2)
val res29: Seq[Int] = List(2, 4, 6, 8, 10)
```

### `.flatten`
戻り値：`Seq(Seq(A), Seq(A))`のように、入れ子になったSeqを一階層の`Seq()`にまとめる。
```
scala> Seq(Seq("a", "b", "c"), Seq(1, 2, 3)).flatten
val res33: Seq[Any] = List(a, b, c, 1, 2, 3)
```

### `.filter(式)`
戻り値：条件を満たす要素だけで構成されたコレクション。ない場合は、空のコレクションを返す。
```
scala> Seq(1, 2, 3, 4, 5).filter(i => i % 2 == 0)
val res25: Seq[Int] = List(2, 4)
```

### `.collect { case式 }`
戻り値：case式にマッチした値のみを集めたList。ない場合は空のListを返す。
```
//例1
scala> Seq(1, 2, 3, 4, 5).collect{ case 1 => "one"; case 5 => "five" }
val res27: Seq[String] = List(one, five)

//例2
scala> Seq(1, 2, 3, 4, 5).collect{ case 0 => "one" }
val res28: Seq[String] = List()
```

### `.collectFirst { case式 }`
戻り値：条件に合致した最初の値をOption[A]型で返す。
```
scala> Seq(1, 2, 3, 4, 5).collectFirst { case i  if i % 2 == 0 => i }
val res36: Option[Int] = Some(2)
```

### `.foldLeft(初期値)((accumulator, value) => 式)`
先頭の要素から順次計算する。  
一つ目の値を用いた計算の結果をaccに入れ、次の値の計算に移行する。　　
```
scala> Seq(1, 2, 3, 4).foldLeft(0: Int)((acc: Int, n: Int) => acc + n)
val res48: Int = 10

//上記は、_を使って次のようにも書ける
scala> Seq(1, 2, 3, 4).foldLeft(0)(_ + _)
val res49: Int = 10
```

### `.foldRight(初期値)((value, accumulator) => 式`
末尾の要素から順次計算する。　　
```
scala> Seq(1, 2, 3, 4).foldRight(0: Int)((acc: Int, n: Int) => acc + n) //((((0 + 1), + 2) + 3) + 4)
val res51: Int = 10

//上記は_を使って次のようにも書ける
scala> Seq(1, 2, 3, 4).foldRight(0)(_ + _)  //(1 + (2 + (3 + (4 + 0))))
val res50: Int = 10 
```

### `.reduce((accumulator, value) => 式)`

### `match式`

### 空のリストの作り方
空リストの書き方は複数あり、型指定もできる。  
```
val l1: List[Int] = Nil

//型指定しない場合、Nilはscala.collection.immutable.Nil.typeの戻り値を指定する。　　
val l2 = Nil:List[Int]

val l3 = List.empty[Int]

//型指定しない場合、List()はList[Nothing]型の戻り値を指定する。　　
val l4 = List[Int]()
```


## 参考
[列トレイト SEQ、INDEXEDSEQ、および LINEARSEQ](https://docs.scala-lang.org/ja/overviews/collections/seqs.html)

