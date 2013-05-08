name := "play-embed-mongo"

version := "0.2-SNAPSHOT"

scalaVersion := "2.10.0"

organization := "info.schleichardt"

libraryDependencies += "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.31"

libraryDependencies += "play" %% "play" % "2.1.0" % "provided"

libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")//for compatibility with Debian Squeeze

publishMavenStyle := true

publishArtifact in Test := false

publishTo <<= version { (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }

pomIncludeRepository := { _ => false }

pomExtra := (
    <url>https://github.com/schleichardt/play-embed-mongo</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:schleichardt/play-embed-mongo.git</url>
        <connection>scm:git:git@github.com:schleichardt/play-embed-mongo.git</connection>
      </scm>
      <developers>
        <developer>
          <id>schleichardt</id>
          <name>Michael Schleichardt</name>
          <url>http://michael.schleichardt.info</url>
        </developer>
      </developers>)