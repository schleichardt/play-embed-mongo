import com.mongodb.{BasicDBObject, MongoClient}
import org.specs2.mutable._
import play.api.test.WithApplication

class ModuleSpec extends Specification {
  "The play-embed-mongo module" should {
    "be able to connect with a MongoDB" in new WithMongoApp {
      val user = usersCollection.findOne()
      user.get("firstname") === "Max"
    }

    "not start automatically a MongoDB if embed.mongo.enabled is not enabled explicitly" in new WithApplication {
      val port = WithMongoApp.getConfiguredPort
      val client = new MongoClient("localhost", port)
      val collection = client.getDB("test").getCollection("users")
      collection.insert(new BasicDBObject) must throwA[com.mongodb.MongoException]
    }
  }
}
