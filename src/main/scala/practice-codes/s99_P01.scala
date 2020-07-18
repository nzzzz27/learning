/*
(*) Find the last element of a list.
Example:
scala> last(List(1, 1, 2, 3, 5, 8))
res0: Int = 8
*/

object s99_P01 {
  
  val numList: List[Int] = List(1, 1, 2, 3, 5, 8)

  def main(args: Array[String]): Unit = {
    println(s"2020/07/12の解答: ${July12(numList)}")      
  }

  def July12(num: List[Int]) = {
    num.last
  }


}
