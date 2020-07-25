object udemyFunctionJuly25 {
  
  def main(args: Array[String]): Unit = {
    println(greeting("Tom", 10))
    println(factorialFunction(3))
    
    println("--- call by name, call by value ---")
    callByName(System.nanoTime())
    callByValue(System.nanoTime())

    println("--- Class ---")
    println("--- Author ---")
    val author = new Writer("J.K", "Rolling", 1965)
    println(author.fullname())

    println("--- Novel ---")
    val novel = new Novel("Harry Potter", 1977, author)
    println(novel.authorAge)
    println(novel.isWrittenBy)

    println("--- Counter ---")
    val counter = new Counter
    counter.inc.print
    counter.inc.inc.print
    counter.inc(5).print

    println("--- Object ---")
    val shape = Shape 
    shape.nameOfShape()

    val shape2 = Shape
    shape2.nameOfShape()
    
    val shape3 = new Shape(2, 2) 
    shape3.calc()

    PersonalInfo.showSecrets
  }

  //  A greeting funtion(name, age) => "Hi, my name is $name and I am $age years old."
  def greeting(name: String, age: Int): String = {
    s"Hi, my name is $name and I am $age years old."
  }

  // Factorial function 1 * 2 * 3 ... n
  def factorialFunction(n: Int): Int = {
    if (n <= 0) 1 else n * factorialFunction(n - 1)
  }

  // --------------------------------
  // call by name, call by value 
  // ---------------------------------
  def callByName(x: => Long): Unit = {
    println(s"by name $x")
    println(s"by name $x")
  }
  def callByValue(x: Long): Unit = {
    println(s"by value $x")
    println(s"by value $x")
  }
  
  // ---------------------------------
  // class
  // ---------------------------------
  class Writer(val firstname: String, val surname: String, val year: Int) {
    def fullname(firstname: String = firstname, surname: String = surname): String = {
      firstname + " " + surname 
    }
  }

  class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
    def authorAge(): Int = {
      yearOfRelease - author.year
    }
    def isWrittenBy(): String = {
      author.fullname()
    } 
  }
  
 class Counter(val num: Int = 0) {
  def inc = { 
    println("increment")
    new Counter(num + 1) //immutability
  }
  def dec = {
    println("decrement")
    new Counter(num - 1)
  }
  def inc(n: Int): Counter = {
    if (n <= 0) this
      else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
      else dec.dec(n - 1)
  }

  def print() = println(num)
 }

 // -------------------------------------
 // Object
 // --------------------------------------
  object Shape {
    println("this is Object")
    def nameOfShape(): Unit = println("SHAPE!!")
  } 
  class Shape(val width: Int, val height: Int) {
    println("this is Class")
    def calc(): Unit = println(this.width * this.height)
  } 
  
  class PersonalInfo (
    val name: String,
    private val secret: String = "My secret is, I hate tomato"
  )

  object PersonalInfo {
    val monica = new PersonalInfo("Monica")
    def showSecrets(): Unit = println(s"${monica.secret}")
  }
}

