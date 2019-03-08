package com.ceiba.unit

import org.scalatestplus.play._
import services.Person
import controllers.PersonController
import play.api.test.Helpers._
import play.api.test.FakeRequest
import play.api.mvc._

import play.api.libs.json._
import play.api.test._
import models.PersonModel
import services.Person
import controllers.PersonController
import play.api.Configuration

class PersonUnitSpec extends PlaySpec with Results {

  val person: Person = new Person {
    override def getPersonById(idParam: Long): Option[PersonModel] = Some(PersonModel(1, "Nelson", 27))
    override def listPeopleByName(nameParam: String): List[PersonModel] = List(
      PersonModel(4, "Jhon Edison", 30),
      PersonModel(5, "Juan Esteban", 31))

  }

  val personNone: Person = new Person {
    override def getPersonById(idParam: Long): Option[PersonModel] = None
  }

  "PersonController" should {

    //Prueba action de encontrar persona por su id.
    "return a valid person with getPersonById" in {
      val controller = new PersonController(stubControllerComponents(), person)
      val result = controller.getPersonById(1).apply(FakeRequest())
      val jsValue = contentAsJson(result)
      val personFound = jsValue.as[PersonModel]
      personFound.name must equal("Nelson")
    }
    //Prueba mensaje persona no encontrada por id.
    "return a NotFound result with getPersonById" in {
      // val controller = new PersonController(stubControllerComponents(), personNone,Configuration())
      val controller = new PersonController(stubControllerComponents(), personNone)
      val result = controller.getPersonById(6).apply(FakeRequest())
      contentAsString(result) must equal("Person not found")
    }

    //Prueba action de listar las personas con nombre que inicen con el filtro.
    "return a valid List filter by name with listPeopleByName" in {
      val controller = new PersonController(stubControllerComponents(), person)
      val result = controller.listPeopleByName("j").apply(FakeRequest())
      val jsValue = contentAsJson(result)
      val seq = jsValue.as[Seq[PersonModel]]
      seq(0).name must equal("Jhon Edison")
      seq(1).name must equal("Juan Esteban")
    }

  }

}