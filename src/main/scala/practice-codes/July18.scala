object July19 { 
  def main(args: Array[String]): Unit = {
    val triangle = new Triangle(10, 30)
    println(triangle.width)
    println(triangle.getArea())
    triangle.draw()
    
    println(job1.name)
    
    cafe.greetings
  }
  
  class Shape(val width: Int, val height: Int) {
    def draw(): Unit = {
      println("不明な図形")
    } 
  }
  
  class Triangle(width: Int, height: Int) extends Shape(width, height) {
    override def draw(): Unit = {
      println("三角形")
    }
    def getArea(): Int = {
      width * height / 2
  }
  }
  
  class Person(val name: String, val age: Int, var nationality: String = "Japan") {
    def greetings(): Unit = {
      println(s"Hi! My name is $name, $age years old, and from $nationality.")
    }
  }
  
  class Japanese(name: String, age: Int) extends Person(name, age) 
   
  trait Job { val name: String }
  val job1 = new { val name = "teacher" } with Job

  abstract case class Shop(name: String, reccomend: String) {
    def greetings(): Unit = {
      println(s"$name へようこそ! おすすめは、$reccomend です！")
    }
  } 

  class Cafe(name: String, reccomend: String) extends Shop (name, reccomend)

  val cafe = new Cafe("Sccala Cafe", "Coffee")
}
