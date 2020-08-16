# `build.sbt`の書き方

```
// プロジェクト名 
name := """play-handson"""

// ドメイン
organization := "com.example"

// 
version := "1.0-SNAPSHOT"

//
lazy val root = (project in file(".")).enablePlugins(PlayScala)

// scalaのバージョン
scalaVersion := "2.13.3"

// 依存性ライブラリ
libraryDependencies ++= Seq(
  guice,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play"    % "5.0.0" % Test,
  "com.typesafe.play"      %% "play-slick"            % "5.0.0",
  "com.typesafe.play"      %% "play-slick-evolutions" % "5.0.0",
  // play-slickの5.0.0ではslick 3.3.2を利用しているため、codegenも同様に3.3.2を指定しています。
  // https://github.com/playframework/play-slick#all-releases
  "com.typesafe.slick"     %% "slick-codegen"         % "3.3.2",
  // 指定すべきmysql-connectorのバージョンは以下のリンク先を参照
  // https://scala-slick.org/doc/3.3.1/database.html
  "mysql"                   % "mysql-connector-java"  % "6.0.6",

  //TypesafeConfig
  "com.typesafe" % "config" % "1.4.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// add code generation task
lazy val slickCodeGen = taskKey[Unit]("execute Slick CodeGen")
slickCodeGen         := (runMain in Compile).toTask(" com.example.SlickCodeGen").value

```
