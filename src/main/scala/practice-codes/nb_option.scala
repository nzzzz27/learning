
object nb_options {
  def main(args: Array[String]): Unit = {
    println(s"Q1: ${nb_optionsQ1(Some(100))}")
    println(s"Q1-2: ${nb_optionsQ1(None)}")
    println(s"Q2: ${nb_optionsQ2(Some(100))}")
    println(s"Q3: ${nb_optionsQ3(Some(Some(100)))}")
  }

  /* Q1
    args...Option[Int]  
    return value...value or 0 　　
  */
  def nb_optionsQ1(num: Option[Int]): Int = {
    num.getOrElse(0)
  }


  /* Q2
     args...Option[Int]
     return value...Some(value + 3) or None
  */
 def nb_optionsQ2(num: Option[Int]): Option[Int] = {
    num.map(i => i * 3)
 }

  
  /* Q3
   * args...Option[Option[Int]]
   * return value...Some(value * 2) or None
  */
  def nb_optionsQ3(num: Option[Option[Int]]): Option[Int] = {
    num.flatMap(i => i.map(i => i * 2))
  }

}
