# 継承
似た構造や目的を持ったオブジェクトを一まとめにして、その機能や要素を他のクラスに継承させることができる。　　
継承元（親）をスーパークラス、継承先（子）をサブクラスという。　　

継承することのメリットとしては、以下が挙げられる。　　
- 独自の型を作れる = Seqなど要素の型が一律でないといけない時に便利
- 持つべき要素を強制的に共通化できる


## 継承の方法
特徴
- class
  - サブクラスを1つしか作成できない
  - 要件からメゾットまでフレキシブルなものに対して使用しやすい。例えば電子レンジのように、色、サイズ、W数など決まっていないもの。
- case class
  - サブクラスを複数持てる
  - 便利メゾットが使えたり、newでインスタンス化する必要がない
- trait
  - サブクラスを複数持てる
  - 振る舞いや見た目など、規格が決まっているものに対して使用しやすい。例えば、USBのようにサイズや差し込み口の形が決定しているもの。


### class
定義方法
```
//スーパークラス
class クラス名(コンストラクタ) { フィールド }

//サブクラス
class サブクラス名(サブクラスのコンストラクタ) extends スーパークラス名(スーパークラスのコンストラクタ) { フィールド }
```
例1：Shapeクラスを継承した、三角形の面積を求めるサブクラスを作る。
```
//スーパークラス
class Shape(val width: Int, val height: Int) {
  def showColor(): Unit = {
      println("これは、青色の図形です。")
    }
   def showShapeName(): Unit = {
     println("図形")
   } 
 }

//サブクラス
class Triangle(width: Int, height: Int) extends Shape(width, height) {
  //スーパークラスのメゾットを上書き
  override def showShapeName(): Unit = {
    println("三角形")
  }
  def getArea(): Int = {
    width * height / 2
  }
}

//インスタンス化
val triangle = new Triangle(10, 30)

//引数の値を取得
triangle.width //10

//.getArea()メゾットを実行
triangle.getArea()

//上書きされたメゾット
triangle.showShapeName()  //三角形

//スーパークラスのメゾット
triangle.draw()  //これは、青色の図形です。
```

### case class


### trait 
