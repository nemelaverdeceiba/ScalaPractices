package controllers

import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent._
import scala.concurrent.duration._
import repositories.PersonRepository
import servicies.PersonService
import play.api.libs.json.JsonValidationError

class PersonController @Inject() (
  controllerComponents: ControllerComponents, personService: PersonService)(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  def insertPerson() = Action.async(parse.json[Person]) { request =>
    insert(request.body)
  }
  private def insert(person: Person): Future[Result] = {
    personService.insertPerson(person)
      .map(_ => Ok("Persona creada de manera exitosa."))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No pudo guardarse el registro"))
      }
  }

  def updatePerson() = Action.async(parse.json[Person]) { request =>
    update(request.body)
  }
  private def update(person: Person): Future[Result] = {
    val id = person.id
    personService.updatePerson(person)
      .map(x =>
        if (x > 0) {
          Ok(s"Persona actualizada de manera exitosa con id $id")
        } else {
          NotFound(s"No  pudo actualizarse el registro con id $id")
        })
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No  pudo actualizarse el registro"))
      }
  }

  def deletePerson() = Action.async(parse.json[Person]) { request =>
    delete(request.body)
  }
  private def delete(person: Person): Future[Result] = {
    val id = person.id
    personService.deletePerson(person)
      .map(x =>
        if (x > 0) {
          Ok(s"Persona eliminada de manera exitosa con id $id")
        } else {
          NotFound(s"No  pudo eliminarse el registro con id $id")
        })
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No pudo eliminarse el registro"))
      }
  }

  def findPersonById() = Action.async(parse.json[Person]) { request =>
    findById(request.body)
  }
  private def findById(person: Person): Future[Result] = {
    //Retorna null
    /*personService.findPersonById(person.id)
      .map(s => Ok(Json.toJson(s)))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No se encontró un registro persona."))
      }*/
    personService.findPersonById(person.id).map(s => s.fold(NotFound("Person not found"))(p => Ok(Json.toJson(p))))
  }

  def listPeople() = Action.async { request =>
    val people: Future[Seq[Person]] = personService.listPeople()
    people.map(s => Ok(Json.toJson(s)))
  }

}