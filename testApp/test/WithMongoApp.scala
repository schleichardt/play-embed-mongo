import com.mongodb.{BasicDBObject, MongoClient}
import play.api.Play
import play.api.test.FakeApplication
import play.api.test.WithApplication

abstract class WithMongoApp(config: Pair[String, String]*) extends
WithApplication(FakeApplication(additionalConfiguration = (config.toMap + ("embed.mongo.enabled" -> "true")))) {
  lazy val port = {
    val portConfigurationKey = "embed.mongo.port"
    Play.configuration.getInt(portConfigurationKey).getOrElse(throw new RuntimeException(s"no port specified with $portConfigurationKey"))
  }
  lazy val client = new MongoClient("localhost", port)
  lazy val db = client.getDB("test")
  lazy val usersCollection = {
    val coll = db.getCollection("users")
    val document = new BasicDBObject("firstname", "Max").append("lastname", "Mustermann")
    coll.insert(document)
    coll
  }
}