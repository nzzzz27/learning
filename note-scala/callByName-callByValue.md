# 名前渡し(call by name) / 値渡し(call by value)

> Scalaでは通常は値渡しを使います。名前渡しに（引数に渡した無限ループが）停止しやすい利点があるのになぜ値渡しを使うのかと訝しむかもしれません。現実の式では値渡しの方が名前渡しよりも指数的に効率的であることが分かっています。なぜなら値渡しは名前渡しが必要とする引数の式の再計算が不要だからです。値渡しに有利になる別の主張はそれが命令的な作用や副作用をずっと上手く扱うことができるというものです。なぜならいつ式が評価されるのかがずっとわかりやすい傾向があるからです。Scalaもまた命令的な側面がありますので、値渡しが標準的な選択となります

```
//共通
def main(args: Array[String]): Unit = {
  callByName(System.nanoTime())
  callByValue(System.nanoTime())
}

//名前渡し
def callByName(x: => Long): Unit = {
  println(s"by name $x")
  println(s"by name $x")
}

//値渡し
def callByValue(x: Long): Unit = {
  println(s"by value $x")
  println(s"by value $x")
}
```
```
//結果
by name 114507293463265
by name 114507293487552

by value 114507293491262
by value 114507293491262
```

## 名前渡し
- 引数には、式が渡されている  
- 値は利用されるたびに、その時の値が代入される  
```
def myFunction(x: => Int): String ... 

val y = myFunction(2)  //xは式
```

## 値渡し
- 引数には値が渡されている  
- 一度決定したら、同じ値が使われ続ける
```
def myFunction(x: Int): String ... 

val y = myFunction(2) //xは値
```

# 参照
[Udemy](https://www.udemy.com/course/rock-the-jvm-scala-for-beginners/learn/lecture/7660632#questions)
[Scalaの名前渡しパラメータが参照する度に評価されるってどこに明記されてるか調べてみた](https://shinharad.hateblo.jp/entry/2018/08/21/233044)
