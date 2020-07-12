
val root = (project in file(".")).settings(
  
  name := "scala-practice",
  version := "0.1",
  scalaVersion := "2.13.2",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint") 
  //scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions"),
  //libraryDependencies += "org.scala" %% "scalaj-http" % "2.3.0"

)


