# case object 
Scala 2系において、挙型を定義する際に活用される。
Scala 3系（Dotty）では、同じ意味を持つ`enum`キーワードが追加されたため、列挙型定義の際に`case object`を使う必要はない。

## case キーワードのメリット
- toStringメソッドが自動生成される
- 自動生成されたtoStringメソッドを使用すると、クラス名と保持している値を返すようになる


## object キーワードのメリット
当該クラスがインスタンス化され、 スコープ内で唯一のインスタンスであることが保証されます。


## 参考サイト
- [case objectについて](https://scalapedia.com/articles/46/case+object%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)
