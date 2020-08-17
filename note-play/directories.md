# [Play アプリケーションの構造](https://www.playframework.com/documentation/ja/2.3.x/Anatomy#Play-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%AE%E6%A7%8B%E9%80%A0)

## DB周りのファイル構造
```
app/
    lib/　　　　　　　　　　#ライブラリに関係するファイルを置く場所 
        model/           #DBからデータを取得するとき用のモデル
        persistence/     #dbから値を取得するメソッドなど(CRUD)を記述
             db/         #dbに格納するためのカラム定義やマッピングなどを記述： Slick Schema （https://scala-slick.org/doc/3.2.1/schemas.html）
```
persistenceとは：
[javax.persistence](https://github.megascus.dev/jpa-spec/docs/apidocs/)パッケージがある。
`javax.persistence`とは、
> Java Persistence APIは永続化とオブジェクト/リレーショナルマッピングの管理のためのAPIです。

オブジェクト/リレーショナルマッピングは、データベースとオブジェクト指向プログラミング言語の間の非互換なデータを変換するプログラミング技法。  


libファイル設定の流れ：  
1. `lib/model`： `lib/persistence/db`用のモデルを定義
2. `lib/persistence/db`： DBから取得したデータを、Scala用に変換。この時モデルに、`lib/model`を指定
3. `lib/persistence`: `lib/persistence/db`のデータを取得/加工し、controllersへ渡す
