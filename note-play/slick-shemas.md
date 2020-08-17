# [Shemas](https://scala-slick.org/doc/3.2.1/schemas.html#schemas)
DBから取得した値をScalaで使えるように変換する作業。マッピング。  

データベースにおける『スキーマ』（schema）とは、データベースの構造で、『データベースの設計図』   

参照：[データベースのスキーマとは？初心者でも理解できる解説](https://offers.jp/media/programming/a_786)   


## [Table Rows](https://scala-slick.org/doc/3.2.1/schemas.html#table-rows)
```
class Coffees(tag: Tag) extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
  def name = column[String]("COF_NAME", O.PrimaryKey)
  def supID = column[Int]("SUP_ID")
  def price = column[Double]("PRICE")
  def sales = column[Int]("SALES", O.Default(0))
  def total = column[Int]("TOTAL", O.Default(0))
  def * = (name, supID, price, sales, total)
}
```

### columメゾット
カラムは、`column`メゾットで定義される  

カラムメゾットの構成要素：  
- Scala type
- column name 
- database(usually in upper-case)

### Scala Type
次のプリミティブ型は、JdbcProfileのJDBCベースのデータベースの追加設定なしでサポートされる  

- Numeric types: Byte, Short, Int, Long, BigDecimal, Float, Double
- LOB types: java.sql.Blob, java.sql.Clob, Array[Byte]
- Date types: java.sql.Date, java.sql.Time, java.sql.Timestamp
- Boolean
- String
- Unit
- java.util.UUID

**Nullableのカラムは、Option[T]型に設定する必要がある。**  

### 列オプション
列オプションは、`O`オブジェクトで定義できる。  
次のオプションは`JdbcProfile`で定義されている。  

- PrimaryKey: Mark the column as a (non-compound) primary key when creating the DDL statements.
- 
- Default[T](defaultValue: T): Specify a default value for inserting data into the table without this column. This information is only used for creating DDL statements so that the database can fill in the missing information.
- 
- SqlType(typeName: String): Use a non-standard database-specific type for the DDL statements (e.g. SqlType("VARCHAR(20)") for a String column).
- 
- AutoInc: Mark the column as an auto-incrementing key when creating the DDL statements. Unlike the other column options, this one also has a meaning outside of DDL creation: Many databases do not allow non-AutoInc columns to be returned when inserting data (often silently ignoring other columns), so Slick will check if the return column is properly marked as AutoInc wHere needed.
