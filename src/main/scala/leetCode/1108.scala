/* LeetCode 1108 
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * A defanged IP address replaces every period "." with "[.]".
 *
 * https://leetcode.com/problems/defanging-an-ip-address/
 */

object leetcode1108 {
  def main(args: Array[String]): Unit = {
    // val address: String = "1.1.1.1"
    val address: String = "255.100.50.0"
    println(defangIPaddr(address))
  }

  def defangIPaddr(address: String): String = {
    // Regexオブジェクトに変換
    val re = "[.]".r
    // マッチした全ての部分を置換
    re.replaceAllIn(address, "[.]")
  }
}
