package com.ceiba.integration

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._

import play.api.libs.json._
import play.api.test._
import models.Person

class PersonIntegrationSpec2 extends PlaySpec with GuiceOneAppPerSuite {

  "PersonController" should {

    //Testing routers.
    "return a valid result with insertPerson" in {
      val call = route(app, FakeRequest(POST, "/insertPerson").
        withBody(Json.toJson(Person(1, "Nelson", "Mejia", 1094918697, "Cedula", 28)))).get
      status(call) mustBe Status.OK
      contentAsString(call) mustBe "Persona creada de manera exitosa."
    }
    "return a valid result with updatePerson" in {
      val call = route(app, FakeRequest(POST, "/updatePerson").
        withBody(Json.toJson(Person(1, "NelsonEditado", "MejiaEditado", 1094918697, "Cedula", 28)))).get
      status(call) mustBe Status.OK
      contentAsString(call) mustBe "Persona actualizada de manera exitosa."
    }

    "return a valid person with findPersonById" in {
      val call = route(app, FakeRequest(GET, "/findPersonById").
        withBody(Json.toJson(Person(1, "", "", 0, "", 19)))).get
      val jsValue = contentAsJson(call)
      val personFound = jsValue.as[Person]
      print(personFound)
      status(call) mustBe Status.OK
    }
    "return a valid List with listPeople" in {
      val call = route(app, FakeRequest(GET, "/listPeople")).get
      val jsValue = contentAsJson(call)
      val people = jsValue.as[Seq[Person]]
      print(people.length)
      status(call) mustBe Status.OK
    }

    "return a valid result with deletePerson" in {
      val call = route(app, FakeRequest(PUT, "/deletePerson").
        withBody(Json.toJson(Person(1, "", "", 0, "", 28)))).get
      status(call) mustBe Status.OK
      contentAsString(call) mustBe "Persona eliminada de manera exitosa."
    }

  }

}