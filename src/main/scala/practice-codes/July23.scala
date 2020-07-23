object July23 {
  def main(args: Array[String]) = {
    val listInt: List[Int] = List(1, 2, 3, 4, 5)
   
    println("//--- P01 ---")
    println(s99_P01.last_1(listInt))
    println(s99_P01.last_2(listInt))
    println(s99_P01.last_3(listInt))

    println("//--- P02 ---")
    println(s99_P02.penultimate_1(listInt))
    println(s99_P02.penultimate_2(listInt))

  }
  
  /* (*) Find the last element of a list.
     Example:
     scala> last(List(1, 1, 2, 3, 5, 8))
     res0: Int = 8
  */ 
  object s99_P01 {
    def last_1(n: List[Int]): Int = {
      //例外が発生する
      n.last
    }
    
    def last_2(n: List[Int]): Int = {
      n.lastOption.getOrElse(0)
    }

    def last_3(n: List[Int]): Int = {
      n match {
        case head :: Nil => head
        case _ :: tail   => last_3(tail)
        case _           => throw new NoSuchElementException
      }
    }
  }

  object s99_P02 {
    /* (*) Find the last but one element of a list.
       Example:
       scala> penultimate(List(1, 1, 2, 3, 5, 8))
       res0: Int = 5
    */
    def penultimate_1(n: List[Int]): Int = {
      val listLength: Int = n.length
      n(listLength - 2)
    }

    def penultimate_2(n: List[Int]): Int = {
      n match {
        case head :: _ :: Nil => head
        case _ :: tail => penultimate_2(tail)
        case _           => throw new NoSuchElementException
      }
    }
  }
}
