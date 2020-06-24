/*
P04 (*) Find the number of elements of a list.
Example:
scala> length(List(1, 1, 2, 3, 5, 8))
res0: Int = 6
 */

object P04_200624 {
  val list = List(1, 1, 2, 3, 5, 8)

  def getLength[A](list: List[A], acc: Int): Int = {
    list match {
      case Nil => 0
      case x::Nil => acc + 1
      case x::xs => getLength(xs, acc + 1)
    }
  }
  val result1 = getLength(list, 0)
  println(s"result1 = ${result1}")

}



object P04_200622 {
  val list = List(1, 1, 2, 3, 5, 8)

  val result1 = list.length
  println(s"result1 = ${result1}")

  def getNumberOfElements[A](list: List[A]): Int = {
    var i = 0
    list match {
      case Nil => 0
      case x::xs => {
        getNumberOfElements(xs) + 1
      }
    }
  }
  val result2 = getNumberOfElements(list)
  println(s"result2 = ${result2}")

}
