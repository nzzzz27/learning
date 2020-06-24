// P01 (*) Find the last element of a list.
//     Example:
//     scala> last(List(1, 1, 2, 3, 5, 8))
//     res0: Int = 8


object P01_200624 {
  val list = List(1, 1, 2, 3, 5, 8)

  val result1 = list.last
  println(s"result1 = ${result1}")

  def getLastElement[A](list: List[A]): A = {
    list match {
      case x::Nil => x
      case x::xs => getLastElement(xs)
    }
  }
  val result2 = getLastElement(list)
  println(s"result2 = ${result2}")
}















object P01_200621 {
  val list1: List[Int] = List(1, 1, 2, 3, 5, 8)

  val result1 = list1.last
  println(s"result1 = ${result1}")
  val result2 = list1(list1.length - 1)
  println(s"result2 = ${result2}")

  def findLastElement[A](list: List[A]): A = {
    list match {
      case a::Nil => a
      case x::xs => findLastElement(xs)
      case _ => throw new NoSuchElementException
    }
  }
  val result3 = findLastElement(list1)
  println(s"result3 = ${result3}")
}
