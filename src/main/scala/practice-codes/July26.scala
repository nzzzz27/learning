object July26 {
  def main(args: Array[String]): Unit = {
    println(aMap)
    println(aForeach)
    println(aFor)
  }
  
  val intList: List[Int] = List(1, 2, 3, 4, 5)

  val aMap: List[Int] = intList.map(_ * 2)
  val aForeach: Unit = intList.foreach(_ * 2)
  val aFor: Seq[Int] = for (i <- 1 to intList.length) yield (i * 2)
  
}
