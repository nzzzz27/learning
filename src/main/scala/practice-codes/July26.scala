import java.io.File

object July26 {
  def main(args: Array[String]): Unit = {
    println(aMap)
    println(aForeach)
    println(aFor)
    //whileLoop
    //doWhileLoop
    
    val fileHere = (new java.io.File("./note-scala")).listFiles
    for (file <- fileHere if file.getName.endsWith("class.md")) file
  }
  
  val intList: List[Int] = List(1, 2, 3, 4, 5)

  val aMap: List[Int] = intList.map(_ * 2)
  val aForeach: Unit = intList.foreach(_ * 2)
  val aFor: Seq[Int] = for (i <- 1 to intList.length) yield (i * 2)
  
  var i: Int = 0
  def whileLoop(): Unit = {
    while (i < 5) {
      i += 1
      i
    }
  }
  
  def doWhileLoop(): Unit = {
    do {
      i += 1
      println(i)
    } while (i < 5)
  }
  
  
  for (i <- 0 to 5) println(i)
}
