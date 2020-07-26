# 制御構造
式：意味のある値を結果として生み出すもの。Unit型は意味のない値。  

## `if`式
```
if (条件式) 真の時の処理 else 偽の時の処理
```

```
scala> val args: Array[String] = Array()
val args: Array[String] = Array()

scala> val filename = if (!args.isEmpty) args(0) else "default.txt"
val filename: String = default.txt
```

## `for`式
戻り値の型：ジェネレータ（`<-`)で決定する  
```
//Unit型を返却
for (i <- 0 to 5) println(i)

//結果
0
1
2
3
4
5

//Vector型で返却
val intList: List[Int] = List(1, 2, 3)
val aFor: Seq[Int] = for (i <- 1 to intList.length) yield (i * 2)

//結果
Vector(2, 4, 6, 8, 10)
```

フィルタリング
```
import java.io.File

val fileHere = (new java.io.File("./note-scala")).listFiles
for (file <- fileHere if file.getName.endsWith("class.md")) println(file)

//結果
./note-scala/class.md
```

## `while`ループ
戻り値の型：Unit型  
whileループは値を生み出さないので、プログラムに何らかの違いを生むために入出力処理を実行するか`var`を更新しる必要がある。そのため、**一般的にScalaでは使用を避けることが推奨されている。**
```
while (条件式) {
    真の時の処理
}
```
```
var i: Int = 0
while (i < 5) {
  i += 1
  println(i)
}

//結果
1
2
3
4
5
```

## `do while`ループ
戻り値の型：Unit型  
whileループと同じ理由で、一般的に被推奨。  
```
var i: Int = 0
do {
  i += 1
  println(i)
} while (i < 5)

//結果
1
2
3
4
5
```

