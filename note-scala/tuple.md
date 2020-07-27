# [tuple](https://github.com/scala/scala/tree/2.13.x/src/library/scala)
- tuple(タプル)は、複数のフィールドをひとまとめにして扱うことができます。
- 複数型の組み合わせを、2〜22個作ることができる(Scalaの標準ライブラリ FunctionNで定義されているのが22まで)
- タプルのN番目の要素にアクセスするときは、`._N`と書く（Nは１スタート）
- タプルの分解にはmatch式を使うことが多い
- どの値が何番目に入っているか一目でわかりにくいため、複数人が関わる開発ではミスを生む原因になる。　　


使用例）

```
val sample1: (Int, Int, Int) = (10, 20, 30)

val sample2: (String, Int) = ("abc", 100)
```

## 使い方
### インスタンスの生成  
```
scala> val persons = ("Taro", "Jiro")
persons: (String, String) = (Taro,Jiro)
```

### パラメータ取得
tupleの要素がN個存在するとき、それぞれの要素にアクセスするには`tuple._N`
```
scala> persons._1
res0: String = Taro
```

### いろんな書き方
全てのタプルは、以下のようにも書ける。
```
Tuple4(1, 2, 3, 4)

Tuple(1, 2)
```


1つの要素からなるタプルは、明示すれば使える。
```
//同じ意味
Tuple1("apple")

Tuple("apple")
```


2つの要素からなるタプルはPairという特別なクラス（オブジェクト）がある。ので、以下の書き方もできる。
```
//同じ意味
(1, "apple")

1 -> "apple"

Pair(1, "apple")

Tuple2(1, "apple")

```  

3つの要素からなるタプルは、Tripleという特別なクラス（オブジェクトがある）。ので、以下の書き方もできる。
```
//同じ意味
(1, 2, 3)

Triple(1, 2, 3)

Tuple3(1, 2, 3)
```

参考サイト：http://www.ne.jp/asahi/hishidama/home/tech/scala/tuple.html  
