/*
P02 (*) Find the last but one element of a list.
Example:
scala> penultimate(List(1, 1, 2, 3, 5, 8))
res0: Int = 5
 */

object P02_200624 {
  val list: List[Int] = List(1, 1, 2, 3, 5, 8)

  val result1 = list(list.length - 2)
  println(s"result1 = ${result1}")

  def getTheLastButOneElement[A](list: List[A]): A = {
      list match {
        case x::xs::Nil => x
        case x::xs => getTheLastButOneElement(xs)
      }
  }
  val result2 = getTheLastButOneElement(list)
  println(s"result2 = ${result2}")
}



object P02_200621 {
  val list: List[Int] = List(1, 1, 2, 3, 5, 8)


  val result1 = list.init.last
  println(s"result1 = ${result1}")


  def getLastSingleElement[A](list: List[A]): A = {
    list match {
      case x::_::Nil => x
      case _::xs => getLastSingleElement(xs)
    }
  }
  val result2 = getLastSingleElement(list)
  println(s"result2 = ${result2}")


  val result3 = list(list.length - 2)
  println(s"result3 = ${result3}")
}
