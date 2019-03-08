package controllers

import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent._
import scala.concurrent.duration._
import repositories.PersonRepository

class PersonController @Inject() (
  controllerComponents: ControllerComponents)(implicit executionContext: ExecutionContext, personRepository: PersonRepository) extends AbstractController(controllerComponents) {

  def insertPerson() = Action.async(parse.json[Person]) { request =>
    insert(request.body)
  }
  private def insert(person: Person): Future[Result] = {    
    personRepository.insert(person)
      .map(_ => Ok("Persona creada de manera exitosa."))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No pudo guardarse el registro"))
      }    
  }

  def updatePerson() = Action.async(parse.json[Person]) { request =>
    update(request.body)
  }
  private def update(person: Person): Future[Result] = {
    personRepository.update(person)
      .map(_ =>  Ok("Persona actualizada de manera exitosa."))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No  pudo actualizarse el registro"))
      }
  }

  def deletePerson() = Action.async(parse.json[Person]) { request =>
    delete(request.body)
  }
  private def delete(person: Person): Future[Result] = {
    personRepository.delete(person)
      .map(_ => Ok("Persona eliminada de manera exitosa."))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No pudo eliminarse el registro"))
      }
  }

  def findPersonById() = Action.async(parse.json[Person]) { request =>
    findById(request.body)
  }
  private def findById(person: Person): Future[Result] = {
    personRepository.findById(person.id)
      .map(s => Ok(Json.toJson(s)))
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No se encontró un registro persona."))
      }
  }

  def listPeople() = Action.async { request =>
    val people: Future[Seq[Person]] = personRepository.all()
    people.map(s => Ok(Json.toJson(s)))
  }

}