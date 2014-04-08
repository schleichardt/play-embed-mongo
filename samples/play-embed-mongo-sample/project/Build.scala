import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-embed-mongo-sample"
  val appVersion      = "1.0-SNAPSHOT"

  val sonatypeRepo = Seq(
    "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  )

  val localRepo = Seq(
    "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository",
    "Local Ivy Repository" at "file:///"+Path.userHome.absolutePath+"/.ivy2/local"
  )

  val appDependencies = Seq()

  resolvers += "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"


  val main = play.Project( appName,appVersion,appDependencies).settings(
    resolvers ++= sonatypeRepo,
    resolvers ++= localRepo,
    libraryDependencies ++= Seq(
      "play-embed-mongo" % "play-embed-mongo_2.10" % "1.0-SNAPSHOT",
      "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.43",
      "org.reactivemongo" %% "play2-reactivemongo" % "0.11.0-SNAPSHOT",
      "org.specs2"        %% "specs2"              % "1.14"        % "test",
      "junit"              % "junit"               % "4.8"         % "test"
    )
  )
}
