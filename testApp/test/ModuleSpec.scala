import org.specs2.mutable._

class ModuleSpec extends Specification {
  "The play-embed-mongo module" should {
    "be able to connect with a MongoDB" in new WithMongoApp {
      val user = usersCollection.findOne()
      user.get("firstname") === "Max"
    }
  }
}
