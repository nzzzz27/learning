# Collection
複数の値を持つことができるクラス。
Collectionにあたるクラスは、以下のパッケージに内包されている。

## `scala.collection.immutable`
不変なコレクションのパッケージ。明記されない場合はこちらのパッケージが適用される。  
![scala.collection.immutable](https://docs.scala-lang.org/resources/images/tour/collections-immutable-diagram-213.svg)

## `scala.collection.mutable`
可変なコレクションのパッケージ。使用時には明示的にimportしなければいけない。    
![scala.collection.mutable](https://docs.scala-lang.org/resources/images/tour/collections-mutable-diagram-213.svg)  

## `scala.collection`
不変、可変コレクションのスーパークラスのパッケージで、両方のコレクションを含む。  
これらは全て、abstract classまたはtrait。   
![scala.collection](https://docs.scala-lang.org/resources/images/tour/collections-diagram-213.svg)  

## Seqトレイト
Iterable の一種。Seq トレイトには LinearSeq と IndexedSeq という異なる性能を持つ二つの子トレイトがある。

### LinearSeq
> データが直線に並んでいるので、要素が格納されたメモリの場所を計算で取得できるので瞬時にデータが取得できる、というわけです。

### IndexedSeq
> LinkedListでは各要素の位置が各所に散らばっており、計算によって場所が特定できません。
> その代わり、各要素が次の要素への参照を持っています。
> そのため、一気に目的の要素まで飛ぶことができず、常に端から対象の要素まで辿らなくてはならないのです。

## 参考
[可変コレクションおよび不変コレクション](https://docs.scala-lang.org/ja/overviews/collections/overview.html)  
[性能特性](https://docs.scala-lang.org/ja/overviews/collections/performance-characteristics.html)  
[学ぼう！ コレクションフレームワーク](https://medium.com/nextbeat-engineering/%E5%AD%A6%E3%81%BC%E3%81%86-%E3%82%B3%E3%83%AC%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF-4b6ffaaf42d5)  
[ScalaのCollection使い分け](http://kechanzahorumon.hatenadiary.com/entry/2016/02/11/013338)  


