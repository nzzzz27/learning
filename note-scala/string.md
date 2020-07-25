# 文字列の演算子
```
val str: String = "Hello, I am learning Scala"
```
## 文字の切り出し
### `.charAt()`
```
scala> str.charAt(2)
val res0: Char = l
```

### `.substring( , )`
```
scala> str.substring(2, 5)
val res1: String = llo
```

### `.split()`
```
scala> str.split(" ").toList
val res2: List[String] = List(Hello,, I, am, learning, Scala)
```

### `.take()`
```
scala> str.take(2)
val res21: String = He
```

## 検索
### `startsWith()`
```
scala> str.startsWith("hello")
val res5: Boolean = false
```

## 変更
### `.replace()`
```
scala> str.replace(" ", "-")
val res6: String = Hello,-I-am-learning-Scala
```

### `.toLowerCase`
```
scala> str.toLowerCase
val res7: String = hello, i am learning scala
```

## `.reverse`
```
scala> str.reverse
val res11: String = alacS gninrael ma I ,olleH
```

## 長さ取得
### `.length`
```
scala> str.length
val res9: Int = 26
```

## 型変換
### `.toInt`
```
scala> val numberString: String = "50"
val numberString: String = 50

scala> numberString.toInt
val res12: Int = 50
```

## 文字連結
### `:+` / `+:`
```
scala> 'a' +: "2" :+ 'z'
val res19: String = a2z
```

## 文字列補完(string interpolation)
### `s"xxx"`
```
val name = "James"
println(s"Hello, $name")
```

### `f"xxx""`
```
val height = 1.9d
val name = "James"
println(f"$name%s is $height%2.2f meters tall")
```

### `raw"xxx"`
エスケープを実行しない
```
scala> raw"a\nb"
res1: String = a\nb
```

## 参照
[Udemy: 2-9 Smart Operations on String](https://www.udemy.com/course/rock-the-jvm-scala-for-beginners/learn/lecture/7660620#questions)
[文字列の補完](https://docs.scala-lang.org/ja/overviews/core/string-interpolation.html)
