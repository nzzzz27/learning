# Map
連想配列（keyとvalueのペア）。  
```
Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "山田")
```

## 演算 - Mapを作成
### `.empty`
戻り値：空のMap()を作成。要素のあるMap()に対して使用すると、そのMap()を空にして返却する。
```
//例1：キーがInt型で値がString型のマップを作成
scala> var emptyMap: Map[Int, String] = Map.empty
emptyMap: Map[Int,String] = Map()

// 新たに代入
scala> emptyMap = Map(1 -> "one")
mutated emptyMap


//例2：要素のあるMap()に対して使用
scala> Map(1 -> "apple", 2 -> "banana").empty
val res31: scala.collection.immutable.Map[Int,String] = Map()
```

## 演算 - 値を取得
### `.get(key)`
戻り値：Option[A]型。値があればSome()、なければNone。
```
//例1
scala> Map(1 -> "鈴木", 2 -> "佐藤").get(2)
val res0: Option[String] = Some(佐藤)

//例2
scala> Map(1 -> "鈴木", 2 -> "佐藤").get(3)
val res1: Option[String] = None
```

### `.getOrElse(key, 初期値)`
戻り値：keyに合致する値。なければ初期値を返す。
```
//例1
scala> Map(1 -> "鈴木", 2 -> "佐藤").getOrElse(1, "NONE")
val res2: String = 鈴木

//例2
scala> Map(1 -> "鈴木", 2 -> "佐藤").getOrElse(3, "NONE")
val res3: String = NONE
```

### `.keys`
戻り値：Mapに含まれるキーの一覧を取得
```
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").keys
val res21: Iterable[Int] = Set(1, 2, 3)
```

### `.values`
戻り値：Mapに含まれる値の一覧を取得
```
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").values
val res22: Iterable[String] = Iterable(鈴木, 佐藤, 高橋)
```

### `.filterKeys(式)`
戻り値：条件を満たすkeyの値を全て返却。条件に合うkeyが存在しない場合は、空のMapを返す。  
Scala v2.13.0からはこのままでは使用できず、将来的にこの機能のための別メゾットが追加予定。 
現状(2020/7時点)は、`.view.filterKeys(p).toMap)`を使用する。
```
//例1
scala> Map(1 -> "A", 2-> "B", 3 -> "C", 4 -> "D").view.filterKeys(i => i % 2 == 0).toMap
val res42: scala.collection.immutable.Map[Int,String] = Map(2 -> B, 4 -> D)

//例2：条件一致しない場合
scala> Map(1 -> "A", 3 -> "B", 5 -> "C", 7 -> "D").view.filterKeys(i => i % 2 == 0).toMap
val res43: scala.collection.immutable.Map[Int,String] = Map()
```


## 演算 - 加工
### `++`
戻り値：Map()とMap()を連結する
```
//例1
scala> Map(1 -> "鈴木", 2 -> "佐藤") ++ Map(3 -> "A", 4 -> "B")
val res11: scala.collection.immutable.Map[Int,String] = Map(1 -> 鈴木, 2 -> 佐藤, 3 -> A, 4 -> B)

//例2：keyが重複した場合、後のものが優先される
scala> Map(1 -> "鈴木", 2 -> "佐藤") ++ Map(1 -> "A", 2 -> "B")
val res10: scala.collection.immutable.Map[Int,String] = Map(1 -> A, 2 -> B)
```

### `Map() - key`
戻り値：keyの値を削除したMap
```
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋") - 3
val res13: scala.collection.immutable.Map[Int,String] = Map(1 -> 鈴木, 2 -> 佐藤)
```


### `.mapValues(式)`
戻り値：演算を適用した結果のvalueの入ったMap。   
Scala v2.13.0からはこのままでは使用できず、将来的にこの機能のための別メゾットが追加予定。 
現状(2020/7時点)は、`.view.mapvalues(p).toMap)`を使用する。
```
//例1
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").view.mapValues(i => i + "さん").toMap
val res45: scala.collection.immutable.Map[Int,String] = Map(1 -> 鈴木さん, 2 -> 佐藤さん, 3 -> 高橋さん)
```

### `.groupBy`
戻り値：指定した条件でグループ分けされたMap  
keyを指定しない場合、生成されたグループ数に合わせて自動でkeyの数字を割り振る。 
```
以下のようなSeqがあります。
val numbers = Seq(1, 2, 3, 4, 5)

これらを2で割った余りでグループ分けしたMapに変えるには、どうしたらいいでしょうか？

//回答1
scala> Seq(1, 2, 3, 4, 5).groupBy(num => if (num % 2 == 0) 0 else 1)
val res52: scala.collection.immutable.Map[Int,Seq[Int]] = HashMap(0 -> List(2, 4), 1 -> List(1, 3, 5))

//回答2
scala> Seq(1, 2, 3, 4, 5).groupBy(_ % 2)
val res51: scala.collection.immutable.Map[Int,Seq[Int]] = HashMap(0 -> List(2, 4), 1 -> List(1, 3, 5))
```

## 演算 - 存在確認
### `.contains(key)`
戻り値：keyに合致する値があればtrue, なければfalse 
```
//例1
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").contains(2)
val res17: Boolean = true

//例2
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").contains(4)
val res16: Boolean = false
```

### `.isDefinedAt(key)`
戻り値：指定したkeyがMapに存在したらtrue, なければfalse
```
scala> Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "高橋").isDefinedAt(1)
val res20: Boolean = true
```

## 演算 - 型変換
### `.toMap`
2つのタプルを含むSeqを、1つのMapに変換する。
```
scala> Seq((1, 2), (3, 4)).toMap
val res46: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4)
```

