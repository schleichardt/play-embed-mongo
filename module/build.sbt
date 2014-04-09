name := "play-embed-mongo"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.43",
  "com.typesafe.play" %% "play" % "2.2.2"
)     
