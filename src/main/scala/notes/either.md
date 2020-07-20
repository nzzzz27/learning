# [Either](https://github.com/scala/scala/blob/2.13.x/src/library/scala/util/Either.scala#L120) 
例外処理に使用する型。  
第一パラメータ（Left）に「エラー」、第二パラメータ（Right）に成功の値を返す。  
Optionでは情報が足りず、エラーが代数的データ型で定められているときに使う。　　

※代数的データ型：  
代数的データ型とはプログラミング、特に関数型プログラミングや型システムにおいて使われるデータ型である。  
それぞれの代数的データ型の値には、1個以上のコンストラクタがあり、各コンストラクタには0個以上の引数がある。  

## Eitherの値を作る
```
//Left（例外時）の値
val leftEither: Either[String, Int] = Left("string")

//Right（成功時）の値
val rightEither: Either[String, Int] = Right(100)
```

## Eitherの値を取得する
### `.left.get / .right.get`
戻り値：rightもしくはleftの値。存在しなければ、例外を返す。
```
val leftEither : Either[String, Int] = Left("string")
val rightEither: Either[String, Int] = Right(100)

//leftの値を取得
leftEither.left.get // -> string

//rightの値を取得
rightEither.right.get // -> 100
```

### `.left.getOrElse() / .right.getOrElse()`
戻り値：rightもしくはleftの値。存在しなければ、引数に設定した初期値を返す。
```
val leftEither : Either[String, Int] = Left("string")
val rightEither: Either[String, Int] = Right(100)

//leftの値を取得
leftEither.right.getOrElse("none") // -> none

//rightの値を取得
rightEither.left.getOrElse(0) // -> 0
```

### `.match式`
戻り値： 条件に合致したLeftまたはRightを、case式の結果に置き換えて出力
```
//Either型の値を作成
scala> val v:Either[String, Int] = Right(100)
val v: Either[String,Int] = Right(100)

//match式で判定
scala> v match {
     |   case Left(str)  => println("Rightだから値がな��よ")
     |   case Right(int) => println(s"入っている値は${int}だよ")
     | }
入っている値は100だよ
```

### `.map(式)`
戻り値：式の処理を適用した値を、RightまたはLeftに入れて返す。　　
```
// Right型が存在するとき、値が変換されて返却される
val rightEither: Either[String, Int] = Right(100)
val after = rightEither.map(i => i * 10)

println(after) // Right(1000)


// Right型が存在しない時、Leftの値が返ってくる
val leftEither: Either[String, Int] = Left("invalid parameter")
val after = leftEither.map(i => i * 10)

println(after) // Left("invalid parameter")


// Left型にmapを適用したい場合は、.leftをつける
val leftEither: Either[String, Int] = Left("invalid parameter")
val after = leftEither.left.map(s => s"Error! - $s")

println(after) // Left("Error! - invalid parameter")
```

### RightとLeftの処理組み合わせ
- EitherのRightに値があるときに、「.left.get」とすると、例外（NoSuchException)
- EitherのRightに値があるときに、「.left.map(i => i)」とすると、Right(Rightの値) ... A
- EitherのLeftに値があるときに、「.right.get」とすると、例外（NoSuchException）
- EitherのLeftに値があるときに、「.right.map(i => i)」とすると、Left (Leftの値) ...B
- 上記A,Bの場合、map内の値を加工する時は、エラーとなる
















