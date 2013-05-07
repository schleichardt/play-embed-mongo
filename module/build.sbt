name := "play-embed-mongo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.0"

organization := "info.schleichardt"

libraryDependencies += "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.31"

libraryDependencies += "play" %% "play" % "2.1.0" % "provided"

libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")//for compatibility with Debian Squeeze