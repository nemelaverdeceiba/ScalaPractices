package controllers

import javax.inject.Inject

import play.api.db._
import play.api.mvc._
import play.api.cache._
import scala.concurrent.duration._
import scala.concurrent._
import scala.util.{ Success, Failure }
import scala.concurrent.ExecutionContext.Implicits.global
import akka.Done
import play.api.libs.json.Json

class ScalaControllerInject @Inject() (cache: AsyncCacheApi, db: Database, val controllerComponents: ControllerComponents) extends BaseController {

  //JDBC Y CACHE
  def jdbcTest = Action.async { implicit request =>

    val futurePeopleNames: Future[String] = cache.getOrElseUpdate[String]("peopleNames",1.minutes) {
    
      println("entre")
      val conn = db.getConnection()
      var peopleNames = "";
      try {
        val stmt = conn.createStatement
        //val rs = stmt.executeQuery("SELECT 9 as testkey ")
        val rs = stmt.executeQuery("SELECT * from person ")

        while (rs.next()) {
          peopleNames += rs.getString("name") + ", "
        }

      } finally {
        conn.close()
      }
      Future.apply(peopleNames)

    }

    futurePeopleNames.map { b => Ok("People names are: " + b) }
  }

}