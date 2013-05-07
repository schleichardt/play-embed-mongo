import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "testApp"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "junit" % "junit-dep" % "4.11" % "test"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )
}
