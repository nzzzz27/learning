/*
P04 (*) Find the number of elements of a list.
Example:
scala> length(List(1, 1, 2, 3, 5, 8))
res0: Int = 6
 */

object P04 {
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
