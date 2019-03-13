package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import actions.LoggingAction
import scala.concurrent.Future
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (cc: ControllerComponents, config: Configuration, loggingAction: LoggingAction) extends AbstractController(cc) {

  implicit val myCustomCharset = Codec.javaSupported("iso-8859-1")

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    getApplicationName
    Ok(views.html.index())
  }

  // def getApplicationName = Action {
  private def getApplicationName = {
    print(config.get[String]("application.name"))
    //Ok(config.get[String]("application.name"))
  }

  //200
  def getOkMessage = Action {
    implicit request: Request[AnyContent] =>
      Ok("<h1>Ok</h1>").as(HTML)
  }

  //401
  def getUnauthorizedMessage = Action {
    implicit request: Request[AnyContent] =>
      Unauthorized

  }

  //500
  def getInternalServerErrorMessage = Action {
    implicit request: Request[AnyContent] =>
      InternalServerError("Internal server error")
  }

  def redirect = Action {
    Redirect("www.google.com")
  }

  //200
  def getOkMessageWithHeaders = Action {
    Ok("<h1>Ok</h1>").withHeaders(
      CACHE_CONTROL -> "max-age=3600",
      ETAG -> "xx")
  }

  //200
  def getOkMessageAddCookie = Action {
    Ok("<h1>Ok</h1>").withCookies(Cookie("theme", "blue"))
      .bakeCookies()
  }
  //200
  def getOkMessageRemoveCookie = Action {
    Ok("<h1>Ok</h1>").withCookies(Cookie("theme", "blue")).discardingCookies(DiscardingCookie("skin"))
  }

  def withLog = loggingAction {
    Ok("Hello World")
  }

  def withErrorHandler = Action {
    throw new Exception("Illegal argument exception....")
    Ok("Hello World")
  }

  /* def asyncrono = Action.async {
  val futureInt = scala.concurrent.Future { asyncMethod() }
  futureInt.map(i => Ok("Got result: " + i))
}

   def asyncMethod(): Future[Int]={
    Future.successful(42)
  }*/

  //json con jsvalue
  def printJson = Action {

    val json: JsValue = Json.obj(
      "name" -> "Watership Down",
      "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
      "residents" -> Json.arr(
        Json.obj(
          "name" -> "Fiver",
          "age" -> 4,
          "role" -> JsNull),
        Json.obj(
          "name" -> "Bigwig",
          "age" -> 6,
          "role" -> "Owsla")))
    Ok(json)
  }
  

implicit val locationWrites = new Writes[Location] {
  def writes(location: Location) = Json.obj(
    "lat" -> location.lat,
    "long" -> location.long
  )
}  
  
  implicit val placeWrites = new Writes[Place] {
  def writes(place: Place) = Json.obj(
    "name" -> place.name,
    "location" -> place.location,
  )
}  
  
  def listPlaces = Action {
  val json = Json.toJson(Place.list)
  Ok(json)
}
  
  
  //Guardar de json a model
  
  implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
  (JsPath \ "long").read[Double]
)(Location.apply _)

implicit val placeReads: Reads[Place] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "location").read[Location]
)(Place.apply _)

def savePlace = Action(parse.json) { request =>
  val placeResult = request.body.validate[Place]
  placeResult.fold(
    errors => {
      BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
    },
    place => {
      Place.save(place)
      Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+place.name+"' saved.") ))
    }
  )
}
  
  
  //Otra Página
  
  def test() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.test())
  }
  
  def fileUploadPage() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.fileUpload())
  }
  
  //Probar error handling
  def errorHandling()= Action {
    InternalServerError
}
  
   //Probar error handling
  def errorHandling2()= Action {
    InternalServerError("Error interno del servidor")
}

}
