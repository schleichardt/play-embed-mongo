import com.mongodb.{BasicDBObject, MongoClient}
import models.MongoUtils
import org.specs2.mutable._
import play.api.test.WithApplication

class ModuleSpec extends Specification {
  "The play-embed-mongo module" should {
    "be able to connect with a MongoDB on a fixed port" in new WithMongoApp {
      val user = usersCollection.findOne()
      user.get("firstname") === "Max"
    }

    "not start automatically a MongoDB if embed.mongo.enabled is not enabled explicitly" in new WithApplication {
      val collection = MongoUtils.collection("test")("users")
      collection.insert(new BasicDBObject) must throwA[com.mongodb.MongoException]
    }

    "be able to start MongoDB with a free port" in new WithMongoApp("embed.mongo.port" -> "0") {
      //test with sbt -Dembed.mongo.enabled=true -Dembed.mongo.port=0 ~run
      true === false
    }.pendingUntilFixed("play refuses to change the port")
  }
}
