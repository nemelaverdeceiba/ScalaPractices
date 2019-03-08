package com.ceiba.unit

import org.scalatestplus.play._
import services.Calculator
import controllers.CalculatorController
import play.api.test.Helpers._
import play.api.test.FakeRequest
import play.api.mvc._

import play.api.libs.json._
import play.api.test._
import models.CalculatorInData

class CalculatorSpec extends PlaySpec with Results {

  val calculator: Calculator = new Calculator {
    override def sum(a: Int, b: Int): Int = 10
    override def substract(a: Int, b: Int): Int = 20
    override def multiply(a: Int, b: Int): Int = 30
    override def divide(a: Int, b: Int): Int = 40
  }

  "CalculatorController" should {

    //Prueba action de sumar.
    "return a valid result with sum" in {
      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val result = controller.sum(FakeRequest().withBody(Json.toJson(CalculatorInData(1, 2))))
      val jsValue = contentAsJson(result)
      val value1 = (jsValue \ "message").get
      value1.toString() must equal("10")
    }
    //Prueba action de restar.
    "return a valid result with substract" in {
      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val result = controller.substract(FakeRequest().withBody(Json.toJson(CalculatorInData(1, 2))))
      val jsValue = contentAsJson(result)
      val value1 = (jsValue \ "message").get
      value1.toString() must equal("20")
    }

    //Prueba action de multiplicar.
    "return a valid result with multiply" in {
      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val result = controller.multiply(FakeRequest().withBody(Json.toJson(CalculatorInData(1, 2))))
      val jsValue = contentAsJson(result)
      val value1 = (jsValue \ "message").get
      value1.toString() must equal("30")
    }
    //Prueba action de dividir.
    "return a valid result with divide" in {
      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val result = controller.divide(FakeRequest().withBody(Json.toJson(CalculatorInData(1, 2))))
      val jsValue = contentAsJson(result)
      val value1 = (jsValue \ "message").get
      value1.toString() must equal("40")
    }

  }

}