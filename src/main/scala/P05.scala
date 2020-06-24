/*
P05 (*) Reverse a list.
Example:
scala> reverse(List(1, 1, 2, 3, 5, 8))
res0: List[Int] = List(8, 5, 3, 2, 1, 1)
 */


object P05_200624 {
  val list = List(1, 1, 2, 3, 5, 8)

  def reverse[A](list: List[A]): List[A] = {
    list.foldLeft(Nil: List[A])((acc, el) => el :: acc)
  }
  val result1 = reverse(list)
  println(result1)
}


object P05_200622 {
  val list = List(1, 1, 2, 3, 5, 8)

  val result1 = list.reverse
  println(s"result1 = ${result1}")

  def reverseList[A](list: List[A]): List[A] = {
    list match {
      case Nil => Nil
      case x::xs => reverseList(xs)++List(x)
    }
  }
  val result2 = reverseList(list)
  println(s"result2 = ${result2}")

  def reverseList2[A](list: List[A]): List[A] = {
    list.foldLeft(Nil: List[A])((x, y) => y :: x)
  }
  val result3 = reverseList2(list)
  println(s"result3 = ${result3}")
}
