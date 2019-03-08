package com.ceiba.integration

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._

import play.api.libs.json._
import play.api.test._
import models.PersonModel

class PersonIntegrationSpec extends PlaySpec with GuiceOneAppPerSuite {

  "PersonController" should {

    //Testing routers.
    "return a valid person with getPersonById" in {
      val call = route(app, FakeRequest(GET, "/getPersonById/1")).get
      val jsValue = contentAsJson(call)
      val personFound = jsValue.as[PersonModel]
      status(call) mustBe Status.OK
      personFound.name must equal("Nelson")
    }
    "return a NotFound result with getPersonById" in {
      val call = route(app, FakeRequest(GET, "/getPersonById/6")).get
      status(call) mustBe Status.NOT_FOUND
      contentAsString(call) must equal("Person not found")
    }
    "return a valid List filter by name with listPeopleByName" in {
      val call = route(app, FakeRequest(GET, "/listPeopleByName?nameFilter=j")).get
      val jsValue = contentAsJson(call)
      val people = jsValue.as[Seq[PersonModel]]
      status(call) mustBe Status.OK
      people(0).name must equal("Jhon Edison")
      people(1).name must equal("Juan Esteban")
    }

  }

}