# 修飾子
## implicit 
暗黙のクラス。  
コンパイラーは、暗黙のクラスに対してはクラスのコンストラクター引数からクラス自体への暗黙の変換コードを生成する。  

- コンストラクターは1つの引数を取るものでなければいけない
- 他のオブジェクト、クラス、トレイトの中に配置されていなければいけない

```
implicit class RectangleMaker(width: Int) {
    def x(height: Int) = Rectangle(width, height)
}

//implicit修飾子があるため、自動生成される
implicit def RectangleMaker(width: Int) = {
    new RectangleMaker(width)
}

//実行（2つの整数の間にxをいれる）
val myRectangle = 3 x 4   //Rectabgle(3,4)


//上記の流れ
1. Int型にはxというメゾットがないため、コンパイラーはIntからそういうメソットをもつクラスへの暗黙の変換を探す。  
2. 自動生成されたRectabgleMakerを見つける
3. 2で見つけたRectangleMakerのxメゾットに、変換に対する呼び出しを挿入する
4. x呼び出しは型チェックをして、結果を戻す
```

## sealed
`sealed`修飾子が付けられたクラスは、**同一ファイル内に定義さあれたクラスからしか継承できない**。  
なので、`sealed`修飾子がついたクラスを継承するクラスが簡単にわかる。 

```
sealed abstract class Idol

final class Haruka extends Idol
final class Chihaya extends Idol
final class Hibiki extends Idol
```

参考サイト：  
- [【Scala】列挙型を使おう](https://dev.classmethod.jp/articles/scala-algebra-data-type/)
