package controllers

import play.api._
import play.api.mvc._
import com.mongodb.{MongoClient, BasicDBObject}

object Application extends Controller {
  
  def index = Action { implicit request =>
    val updateDoc = new BasicDBObject().append("$inc", new BasicDBObject().append("visits", 1));
    val client = new MongoClient("localhost", Play.current.configuration.getInt("embed.mongo.port").get)
    val collection = client.getDB("test").getCollection("visits")
    val startPageQuery = new BasicDBObject().append("_id", "startpage")
    try {
      collection.update(startPageQuery, updateDoc, true, false)
      val statsDoc = collection.findOne(startPageQuery)
      val visits = statsDoc.get("visits")
      Ok(views.html.index("The page is viewed " + visits + " times."))
    } catch {
      case e: com.mongodb.MongoException => BadRequest("Please start with `sbt -Dembed.mongo.enabled=true ~run` because embed mongo is not enabled by default in this project.")
    }

  }
  
}