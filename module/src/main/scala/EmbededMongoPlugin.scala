package play.embed.mongo

import play.api.{Logger, Plugin, Application}
import java.util.logging.{Logger => JLogger}
import de.flapdoodle.embed.mongo.{Command, MongodStarter, MongodProcess, MongodExecutable}
import de.flapdoodle.embed.mongo.config._
import de.flapdoodle.embed.process.runtime.Network
import java.io.IOException
import de.flapdoodle.embed.mongo.distribution.{Feature, Versions, Version}
import de.flapdoodle.embed.process.distribution.GenericVersion


/**
 * Provides a MongoDB instance for development and testing.
 * Hast to be loaded before any other plugin that connects with MongoDB.
 */
class EmbedMongoPlugin(app: Application) extends Plugin {
  private var mongoExe: MongodExecutable = _
  private var process: MongodProcess = _

  override def enabled = app.configuration.getBoolean("embed.mongo.enabled").getOrElse(false)

  override def onStart() {
    val runtimeConfig = new RuntimeConfigBuilder()
      .defaultsWithLogger(Command.MongoD, JLogger.getLogger(getClass().getName()))
      .build()
    val runtime = MongodStarter.getInstance(runtimeConfig)
    val keyPort = "embed.mongo.port"
    val keyMongoDbVersion = "embed.mongo.dbversion"
    val versionNumber = app.configuration.getString(keyMongoDbVersion).getOrElse(throw new RuntimeException(s"$keyMongoDbVersion is missing in your configuration"))
    val version = Versions.withFeatures(new GenericVersion(versionNumber),Feature.SYNC_DELAY)
    val port = app.configuration.getInt(keyPort).getOrElse(throw new RuntimeException(s"$keyPort is missing in your configuration"))
    val configBuilder= new MongodConfigBuilder()
      .version(version)
      .net(new Net(port,Network.localhostIsIPv6()))

    mongoExe = runtime.prepare(configBuilder.build)
    Logger.info(s"Starting MongoDB on port $port. This might take a while the first time due to the download of MongoDB.")
    try {
      process = mongoExe.start()
    } catch {
      case e: IOException => {
        val message = s"""Maybe the port $port is used by another application. If it was a MongoDB, it might be down now."""
        throw new IOException(message, e)
      }
    }
  }

  override def onStop() {
    Logger.info(s"Stopping MongoDB.")
    try {
      if (mongoExe != null)
        mongoExe.stop()
    } finally {
      if (process != null)
        process.stop()
    }
  }
}

private[mongo] object EmbedMongoPlugin {
  def freePort() = Network.getFreeServerPort
}