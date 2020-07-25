# 特殊なメゾット記法
## 中置記法(infix notation)
```
scala> class Person(val name: String) { 
     |  def hangOut(friendName: String): Unit = println(s"$name hang out with $friendName")
     | } 
scala> val mary = new Person("Mary")

scala> mary hangOut("Tom")  //infix notation
Mary hang out with Tom
```

## `.unary_`
`+`, `-`, `!`, `~`いずれかをつけると、前置演算子として使えるようになる
```
case class A(s: String) {  // val a = A("a")に対し
  def unary_+ = new A("+" + s) //  +a
  def unary_- = new A("-" + s) //  -a
  def unary_! = new A("!" + s) //  !a
  def unary_~ = new A("~" + s) //  ~a
  def unary_* = new A("*" + s) //「*a」とは書けない。「a.unary_*」となる。
}
```

# 参照
[hishimada: メゾットの定義](http://www.ne.jp/asahi/hishidama/home/tech/scala/def.html)
