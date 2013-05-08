package info.schleichardt.play.embed.mongo

import play.api.{Mode, Configuration, GlobalSettings}
import java.io.File

trait DynamicEmbedMongoPort extends GlobalSettings {

  def additionalEmbedMongoPortSettings(port: Int) = Map[String, Any]()

  override def onLoadConfig(config: Configuration, path: File, classloader: ClassLoader, mode: Mode.Mode) = {
    val embedMongoActive = config.getBoolean("embed.mongo.enabled").getOrElse(false)
    def dynamicPortRequested = config.getInt("embed.mongo.port").map(_ == 0).getOrElse(false)
    val intermediate = if (embedMongoActive && dynamicPortRequested) {
      val port = EmbedMongoPlugin.freePort
      config ++ Configuration.from(Map("embed.mongo.port" -> port) ++ additionalEmbedMongoPortSettings(port))
    } else config
    super.onLoadConfig(intermediate, path, classloader, mode)
  }
}
