# Object
Scalaでは、全ての値がオブジェクト。すべてのメゾットは何らかのオブジェクトに所属している。  

```
//定義
object オブジェクト名 {
  val sample = "sample"
}

//値にアクセス
オブジェクト名.smaple  //sample
```

## シングルトンオブジェクト
一つのインスタンスしか生成することができないオブジェクトのこと。Scalaのオブジェクトは、シングルトンオブジェクト。  

```
scala> class Person  //型 + 唯一のインスタンス
class Person

scala> val mary = Person
val mary: Person.type = Person$@62ddef7c

scala> val tom = Person
val tom: Person.type = Person$@62ddef7c

scala> mary == tom　//同じインスタンスなので、trueとなる。
val res0: Boolean = true
```

classで宣言したクラスは複数のインスタンスを生成できるが、objectで宣言されたクラスは一つのインスタンスしか生成できない。  

## コンパニオンオブジェクト
- クラスと同じ名前、同じスコープを持つオブジェクトのこと
- 逆に、そのクラスは、オブジェクトのコンパニオンクラスと呼ばれる  
- コンパニオンクラスやコンパニオンオブジェクトは自身の**コンパニオンのプライベートメンバーにアクセスできる**  
- コンパニオンクラスのインスタンスに特定されないメソッドや値にはコンパニオンオブジェクトを使う  

```
class PersonalInfo (
  val name: String,
  private val secret: String = "My secret is, I hate tomato" //プライベート
)

object PersonalInfo {
  val monica = new PersonalInfo("Monica")
  def showSecrets(): Unit = println(s"${monica.secret}") //プライベートにアクセスできる
}

//結果
PersonalInfo.showSecrets
```


## 参照
[Udemy: 3-14 Scala Objects](https://www.udemy.com/course/rock-the-jvm-scala-for-beginners/learn/lecture/7660652#questions)  
[[Scala] シングルトンオブジェクトとコンパニオンオブジェクト](https://qiita.com/kingzer0314/items/a6b82034f760f024fdfa)  

