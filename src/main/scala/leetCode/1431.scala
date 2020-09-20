/* 
 * 1431. Kids With the Greatest Number of Candies
 * Given the array candies and the integer extraCandies, where candies[i] represents the number of candies that the ith kid has.
 * For each kid check if there is a way to distribute extraCandies among the kids such that he or she can have the greatest number of candies among them. Notice that multiple kids can have the greatest number of candies.
 *
 * https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 */


object LeetCode1431 {
  def main(args: Array[String]): Unit = {
    val candies:Array[Int] = Array(2,3,5,1,3)
    val extraCandies: Int  = 3

    println(kidsWithCandies(candies, extraCandies))
  }
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
    val borderInt: Int = candies.max - extraCandies
    
    candies.map(_ match {
      case int if int >= borderInt => true
      case _                       => false
    })
  }
}
