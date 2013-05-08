package models

import com.mongodb.MongoClient
import play.api.Play

object MongoUtils {
  def mongoPort = Play.current.configuration.getInt("mongo.client.port")
  def newMongoClient = new MongoClient("localhost", mongoPort.get)
  def collection(db: String)(collection: String) = newMongoClient.getDB(db).getCollection(collection)
}