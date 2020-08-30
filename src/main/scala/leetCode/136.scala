/* LeetCode 136 
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * https://leetcode.com/problems/single-number/
 */

object leetcode136 {
  def main(args: Array[String]): Unit = {
    val intArray = Array(2, 2, 1)
    println(singleNumber(intArray))

  }
  
  def singleNumber(nums: Array[Int]) = {
    val nums2: Array[Int] =  nums.distinct  //Array(2, 1)
    //nums2とnumsで値が2個以下のものだけの配列を作成
    nums2.filter(n => nums.filter(_ == n).length == 1)(0)
  }
}
