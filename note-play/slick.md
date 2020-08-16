# Play-slick
playでslickを使うためのプラグイン。  

- [Play公式ドキュメントのSlickの説明](https://www.playframework.com/documentation/2.8.x/PlaySlick#DatabaseConfig-via-runtime-dependency-injection)


## 導入手順
1. [Set up](https://www.playframework.com/documentation/2.8.x/PlaySlick#Setup)
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

### [Play Database Evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions)とは 
データベースを扱うためにPlayが提供しているライブラリ。  
evolutions はデータベースのマイグレーションをおこなうためのライブラリです。  

- [Play Frameworkを使ったWebアプリケーション作成入門3日目](https://hexx.github.io/scala_text/introduction-of-web-application-3rd-day.html)

マイグレーションとは、テーブル定義の変更の履歴などを管理する機能。  
データベースを削除してから作り直すと，DBに保存されている情報が全て削除されてしまいます． こういった事態を回避する方法。  

- [3. データベースマイグレーション](https://densan-labs.net/tech/codefirst/migration.html#:~:text=%E3%83%87%E3%83%BC%E3%82%BF%E3%83%99%E3%83%BC%E3%82%B9%E3%82%92%E5%89%8A%E9%99%A4%E3%81%97%E3%81%A6,%E5%89%8A%E9%99%A4%E3%81%95%E3%82%8C%E3%81%A6%E3%81%97%E3%81%BE%E3%81%84%E3%81%BE%E3%81%99%EF%BC%8E&text=%E3%83%9E%E3%82%A4%E3%82%B0%E3%83%AC%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%A8%E3%81%AF%E3%80%81DB%E3%81%AB,%E8%A1%8C%E3%81%86%E3%81%9F%E3%82%81%E3%81%AE%E6%A9%9F%E8%83%BD%E3%81%A7%E3%81%99%E3%80%82)


### [JDBC](https://www.oracle.com/java/technologies/javase/javase-tech-database.html)とは
データベースを扱うためにPlayが提供しているライブラリ。  
JavaのデータベースライブラリのJDBCを使ってデータベースにアクセスするための機能や、データベースをテストをするためのライブラリです。

- [Play Frameworkを使ったWebアプリケーション作成入門3日目](https://hexx.github.io/scala_text/introduction-of-web-application-3rd-day.html)


### [Slick](https://scala-slick.org/)
データベースへアクセスするためのライブラリで、Playに標準搭載されている。  
SQLの代わりのクエリ文をScalaに書くことができる。  

同じライブラリに、[Scalike](http://scalikejdbc.org/)というものもある。  


2. [Database Configuration](https://www.playframework.com/documentation/2.8.x/PlaySlick#Database-Configuration)を記載する
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


## slick-codegen
PlayでDataBaseに接続する時に使用するSlickのTableと入出力で使用するインスタンスのcase classのコードを、DBのスキーマから自動生成してくれるツール



## TypesafeConfig

