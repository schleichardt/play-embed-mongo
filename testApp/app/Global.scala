import play.api.GlobalSettings
import info.schleichardt.play.embed.mongo.DynamicEmbedMongoPort

object Global extends GlobalSettings with DynamicEmbedMongoPort {
  override def additionalEmbedMongoPortSettings(port: Int) = Map("mongo.client.port" -> port.toString)
}