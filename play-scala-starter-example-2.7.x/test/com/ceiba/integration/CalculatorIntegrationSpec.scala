package com.ceiba.integration

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._

import play.api.libs.json._
import play.api.test._
import models.CalculatorInData

class CalculatorSpec extends PlaySpec with GuiceOneAppPerSuite {

  "CalculatorController" should {

    //Testing routers.
    "return the result of sum" in {
      val jsValue = contentAsJson(route(app, FakeRequest(POST, "/sum").withBody(Json.toJson(CalculatorInData(20, 10)))).get)
      val value1 = (jsValue \ "message").get
      value1.toString() mustBe "30"
    }
    "return the result of susbstract" in {
      val jsValue = contentAsJson(route(app, FakeRequest(POST, "/substract").withBody(Json.toJson(CalculatorInData(20, 10)))).get)
      val value1 = (jsValue \ "message").get
      value1.toString() mustBe "10"
    }
    "return the result of multiply" in {
      val jsValue = contentAsJson(route(app, FakeRequest(POST, "/multiply").withBody(Json.toJson(CalculatorInData(20, 10)))).get)
      val value1 = (jsValue \ "message").get
      value1.toString() mustBe "200"
    }
    "return  the result of divide" in {
      val jsValue = contentAsJson(route(app, FakeRequest(POST, "/divide").withBody(Json.toJson(CalculatorInData(20, 10)))).get)
      val value1 = (jsValue \ "message").get
      value1.toString() mustBe "2"
    }

  }

}