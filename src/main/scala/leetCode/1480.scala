/* Leet Code 1480
 * https://leetcode.com/problems/running-sum-of-1d-array/
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
   Return the running sum of nums.
*/

object LeetCode1480 {
  def main(args: Array[String]): Unit = {
    val nums:Array[Int] = Array(1,2,3,4)
    val result = runningSum(nums)
    println(result)
  }

  def runningSum(nums: Array[Int]): Array[Int] = {
    nums.foldLeft(Array(0): Array[Int])((acc, value) => {
      acc :+ acc.map(_ + value).last
    }).tail
  }
}
