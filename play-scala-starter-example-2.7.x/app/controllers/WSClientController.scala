package controllers

import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Json

class WSClientController @Inject() (cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

  //Consume Get
  def consumeListPlaces = Action.async { implicit request =>
    //Request
    val request: WSRequest = ws.url("http://localhost:9001/listPlaces")
    val complexRequest: WSRequest =
      request.addHttpHeaders("Accept" -> "application/json").withRequestTimeout(10000.millis)
    val futureResponse: Future[WSResponse] = complexRequest.get()
    //Processing response
    futureResponse.map {
      response => Ok(response.body)
    }

  }
  
    //Consume Post
  def consumeSavePlace = Action.async { implicit request =>

    val data= Json.obj(
      "name" -> "Nuthanger Farm",
      "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197))

    //Request
    val request: WSRequest = ws.url("http://localhost:9001/savePlace")
    val complexRequest: WSRequest =
      request.addHttpHeaders("Accept" -> "application/json").withRequestTimeout(10000.millis)
    val futureResponse: Future[WSResponse] = complexRequest.post(data)
    //Processing response
    futureResponse.map {
      response => Ok(response.body)
    }

  }

}