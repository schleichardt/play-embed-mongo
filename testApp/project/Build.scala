import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName = "testApp"
  val appVersion = "0.2-SNAPSHOT"

  val appDependencies = Seq(
    "junit" % "junit-dep" % "4.11" % "test"
    , "info.schleichardt" %% "play-embed-mongo" % appVersion
    , "org.mongodb" % "mongo-java-driver" % "2.11.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    logBuffered in Test := false
  )
}
