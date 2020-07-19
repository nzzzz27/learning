object July18 { 
  def main(args: Array[String]): Unit = {
    val user1 = new User("Tom", 20)
    user1.greet()

    val point1 = new Point
    point1.x = 99
    point1.y = 101

    println(point1.x)
    println(point1.y)
  }
  
  class User(val name: String, val age: Int) {
    def greet(_name: String = name , _age: Int = age): Unit = {
      println(s"Hello, I am ${name} and ${age} years old}")
    }
  }
  
  class Point {
    private var _x = 0
    private var _y = 0
    private val bound = 100

    def x = _x
    def x_= (newValue: Int): Unit = {
      if (newValue < bound) _x = newValue else printWarning
    }

    def y = _y
    def y_= (newValue: Int): Unit = {
     if (newValue < bound) _y = newValue else printWarning
    }

    private def printWarning = println("WARNING: Out of bounds")
  }

}
