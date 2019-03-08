package controllers

import javax.inject._
import play.api.mvc._
import models.PersonModel
import play.api.libs.json._
import services.Person
import scala.concurrent.Future
import play.api.Configuration

class PersonController @Inject() (
  cc: ControllerComponents, personService: Person) extends AbstractController(cc) {
  // cc: ControllerComponents, personService: Person,config: Configuration) extends AbstractController(cc) {
  //cc: MessagesControllerComponents, personService: Person,config: Configuration) extends MessagesAbstractController(cc) {

  def getPersonById(id: Long) = Action {
    // Future.apply(body)
    //1 fold
    //2 getOrElse
    //3
    personService.getPersonById(id) match {
      case Some(x) => Ok(Json.toJson(x))
      case None    => NotFound("Person not found")
      //case None    => NotFound(config.get[String]("person.NotFound"))
      //case None    => NotFound("person.NotFound")
    }

  }

  def listPeopleByName(nameFilter: String) = Action {
    val result = personService.listPeopleByName(nameFilter);
    Ok(Json.toJson(result))
  }

}