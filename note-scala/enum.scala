# 列挙型（Enum）  
使用可能な定数を列挙する書き方。  

メリット：  
- 使用可能な定数を明確化できる  
- 定数をのクラスで使い回せる

## 定義の例
### シンプルな列挙型
```
sealed trait SampleEnum

case object A extends SampleEnum
case object B extends SampleEnum
case object C extends SampleEnum
case object D extends SampleEnum
```

### 値を持った列挙型
```
sealed abstract class IndexedEnum(val index: Int)

case object IA extends IndexedEnum(1)
case object IB extends IndexedEnum(2)
case object IC extends IndexedEnum(3)
case object ID extends IndexedEnum(4)


val ie: IndexedEnum = IB
require(ie.index == 2)
```
