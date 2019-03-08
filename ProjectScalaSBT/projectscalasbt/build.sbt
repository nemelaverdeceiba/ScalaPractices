name := """ProjectScalaSBT"""
organization := "com.ceiba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test


//Slick base de datos.
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
//libraryDependencies ++= Seq(
//  "com.typesafe.play" %% "play-slick" % "3.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
//)

//h2
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

//JDBC
//libraryDependencies += jdbc

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ceiba.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ceiba.binders._"


//Play Cache
libraryDependencies ++= Seq(
  ehcache
)
libraryDependencies += jcache
