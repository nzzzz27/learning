# Play-slick
SlickをPlayのライフサイクルに組み込んでくれるツール。  
設定情報からDB connectionを作成したり破棄したりの管理を良い感じにやってくれる  

- [Play公式ドキュメントのSlickの説明](https://www.playframework.com/documentation/2.8.x/PlaySlick#DatabaseConfig-via-runtime-dependency-injection)


## 導入手順
### 1. [Set up](https://www.playframework.com/documentation/2.8.x/PlaySlick#Setup)
```
// build.sbt

libraryDependencies ++= Seq(
  // evolutionsを有効にする
  jdbc,
  evolutions,

  // slickのバージョン指定 
  "com.typesafe.play" %% "play-slick" % "4.0.0",

  // evolutionsのバージョン指定
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0"
  
  // JDBC driverを指定
  "mysql"             % "mysql-connector-java"  % "6.0.6",
)
```

#### [Play Database Evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions)とは 
データベースを扱うためにPlayが提供しているライブラリ。  
evolutions はデータベースのマイグレーションをおこなうためのライブラリです。  

- [Play Frameworkを使ったWebアプリケーション作成入門3日目](https://hexx.github.io/scala_text/introduction-of-web-application-3rd-day.html)

マイグレーションとは、テーブル定義の変更の履歴などを管理する機能。  
データベースを削除してから作り直すと，DBに保存されている情報が全て削除されてしまいます． こういった事態を回避する方法。  

- [3. データベースマイグレーション](https://densan-labs.net/tech/codefirst/migration.html#:~:text=%E3%83%87%E3%83%BC%E3%82%BF%E3%83%99%E3%83%BC%E3%82%B9%E3%82%92%E5%89%8A%E9%99%A4%E3%81%97%E3%81%A6,%E5%89%8A%E9%99%A4%E3%81%95%E3%82%8C%E3%81%A6%E3%81%97%E3%81%BE%E3%81%84%E3%81%BE%E3%81%99%EF%BC%8E&text=%E3%83%9E%E3%82%A4%E3%82%B0%E3%83%AC%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%A8%E3%81%AF%E3%80%81DB%E3%81%AB,%E8%A1%8C%E3%81%86%E3%81%9F%E3%82%81%E3%81%AE%E6%A9%9F%E8%83%BD%E3%81%A7%E3%81%99%E3%80%82)


#### [JDBC](https://www.oracle.com/java/technologies/javase/javase-tech-database.html)とは
データベースを扱うためにPlayが提供しているライブラリ。  
JavaのデータベースライブラリのJDBCを使ってデータベースにアクセスするための機能や、データベースをテストをするためのライブラリです。

- [Play Frameworkを使ったWebアプリケーション作成入門3日目](https://hexx.github.io/scala_text/introduction-of-web-application-3rd-day.html)


#### [Slick](https://scala-slick.org/)とは
データベースへアクセスするためのライブラリで、Playに標準搭載されている。  
SQLの代わりのクエリ文をScalaに書くことができる。  

同じライブラリに、[Scalike](http://scalikejdbc.org/)というものもある。  


### 2. [Database Configuration](https://www.playframework.com/documentation/2.8.x/PlaySlick#Database-Configuration)を記載する
application.confに有効なSlickドライバーとデータベース構成を記載する。  
この時、デフォルトのSlickデータベースは`default`という名前が推奨される。  

configに設定できる項目は、[Database.forConfig](https://scala-slick.org/doc/3.1.0/api/index.html#slick.jdbc.JdbcBackend$DatabaseFactoryDef@forConfig%28path:String,config:com.typesafe.config.Config,driver:java.sql.Driver,classLoader:ClassLoader%29:JdbcBackend.this.Database)を参照。（タブを開く事）

一行ずつ書く場合の例：  
```
//application.conf

# Default database configuration
slick.dbs.default.profile="slick.jdbc.H2Profile$"
slick.dbs.default.db.driver="org.h2.Driver"
slick.dbs.default.db.url="jdbc:h2:mem:play"
```

`json`形式で書く場合の例：  
```
slick {
  dbs {
    default {
      profile = "slick.jdbc.MySQLProfile$"
      db {
        driver   = com.mysql.cj.jdbc.Driver,
        url      = "jdbc:mysql://db:3306/twitter?useSSL=false",
        user     = "root",
        password = "root",
        connectionPool = disabled
      }
    }
  }
  codegen {
    outputDir = "./output/codegen"
  }
}
```

- `slick.dbs.default`の`default`部分は、別の名前でもOK 
- `slick.dbs.default.profile`の部分は、Slickプロファイルの構成に使用される  
- `slick.dbs.default.db.driver`は、Slickのバックエンドで使用される基礎となるJDBCドライバー
- サポートしているDB種類は、[Slick](https://scala-slick.org/docs/)公式ページのSlickバージョンのページの、`Supported Databases`の項目で確認できる


## [使い方](https://www.playframework.com/documentation/2.8.x/PlaySlick#Usage0)


## [Slick-codegen](https://scala-slick.org/doc/3.2.1/code-generation.html)
PlayでDataBaseに接続する時に使用するSlickのTableと入出力で使用するインスタンスのcase classのコードを、DBのスキーマから自動生成してくれるツール。  
DBとの接続に必要。  

単独で使う方法と、`build.sbt`と統合させる使い方がある。  
デフォルトでは、コードジェネレーターは、テーブルクラス、対応するTableQuery値を生成します。これらは、コレクションのような方法で使用でき、値の完全な行を保持するためのケースクラスも同様です。

### スキーマとは
データベースの構造で、『データベースの設計図』。  
『外部スキーマ』『概念スキーマ』『内部スキーマ』の3構造に分けられる。  

- 外部スキーマ：概念スキーマで定義された論理データから、必要なデータを取り出したビューなどに相当する部分
- 概念スキーマ：データベース上の論理データで、保持するデータの要素や、データ同士の関係を定義するテーブルに相当する部分
- 内部スキーマ：概念スキーマで定義された論理データを、具体的にどのように格納するかを定義する部分

参考サイト：  
- [データベースのスキーマとは？初心者でも理解できる解説](https://offers.jp/media/programming/a_786)

### 使い方1：[Stand alone](https://scala-slick.org/doc/3.2.1/code-generation.html#standalone-use)
#### 1. `build.sbt`にライブラリを追加
```
// build.sbt
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.2.1"
```

#### 2. 実行するためのコードを書く
詳しい書き方は公式サイトを参照。  
```
// app/tasks/SlickCodeGen.scala 
// (ファイル名は任意)
slick.codegen.SourceCodeGenerator.main(
  Array(uri, outputFolder)
)
```
```
uri: Config URL and/or fragment for path in typesafe config, e.g. url#slick.db.default
profile: Fully qualified name of the profile class, e.g. slick.jdbc.H2Profile
jdbcDriver: Fully qualified name of the JDBC driver class, e.g. org.h2.Driver
url: JDBC url, e.g. jdbc:postgresql://localhost/test
outputFolder: Place where the package folder structure should be put
pkg: Scala package the generated code should be places in
user: database connection user name
password: database connection password
```

### 使い方2：[sbtに結合する](https://scala-slick.org/doc/3.2.1/code-generation.html#integrated-into-sbt)
公式サイトを参照。  


### [カスタマイズ](https://scala-slick.org/doc/3.2.1/code-generation.html#customization)
コードジェネレーターの実装は、完全な出力のさまざまなフラグメントを処理するサブジェネレーターの小さな階層に構造化されています。  
各サブジェネレーターの実装は、対応するファクトリーメソッドをオーバーライドすることにより、カスタマイズされたものと交換できます。

[SouceCodeGenerator](https://scala-slick.org/doc/3.2.1/codegen-api/index.html#slick.codegen.SourceCodeGenerator)には、各テーブルのサブジェネレータを生成するためのTableメゾットが内包されている。  

カスタマイズの詳細は、SourceCodeGen APIのページを参照する。  


## TypesafeConfig

