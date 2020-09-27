## `signnum`（Java）
指定された値の符号を判定できます。引数が正数であれば1.0を、引数が負数であれば-1.0を、ゼロであれば0.0を、それぞれ返します。  
ただし、引数がNaNの場合は、戻り値もNaNとなります。  

```
package com.example.mynavi.math;
 
public class MathSignum {
  public static void main(String[] args) {
    System.out.println(Math.signum(-3));    // 結果：-1.0
    System.out.println(Math.signum(0)); // 結果：0.0
    System.out.println(Math.signum(15));    // 結果：1.0
  }
}
```
https://java-code.jp/203
