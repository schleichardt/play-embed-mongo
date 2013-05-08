play-embed-mongo
=================

A play framework module for [Embedded MongoDB](https://github.com/flapdoodle-oss/embedmongo.flapdoodle.de).
It just for setting up a MongoDB for development and testing.

This module is a work in progress.

## Usage
* add `"info.schleichardt" %% "play-embed-mongo" % "0.1-SNAPSHOT"` to your `appDependencies` in project/Build.scala
* add `resolvers += Resolver.sonatypeRepo("snapshots")` to your project/Build.scala
* add `380:info.schleichardt.play.embed.mongo.EmbedMongoPlugin` to your conf/play.plugins file
* your conf/application.conf file

```
#should be false in production!!!
embed.mongo.enabled=true
embed.mongo.port=27017
embed.mongo.dbversion="2.4.3"
```

* optinally to use a random port: `embed.mongo.port=0` and put in your Global.scala

```
import play.api.GlobalSettings
import info.schleichardt.play.embed.mongo.DynamicEmbedMongoPort

object Global extends GlobalSettings with DynamicEmbedMongoPort {
  //replace "mongo.client.port" with your settings for your driver
  override def additionalEmbedMongoPortSettings(port: Int) = Map("mongo.client.port" -> port.toString)
}
```

## Licence
This software is licensed under the Apache 2 license, quoted below.

Copyright Schleichardt

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

