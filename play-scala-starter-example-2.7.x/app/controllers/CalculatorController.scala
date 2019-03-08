package controllers

import javax.inject._

import play.api.mvc._
import services.Calculator
import models.CalculatorInData
import play.api.libs.json._

@Singleton
class CalculatorController @Inject() (
  cc:         ControllerComponents,
  calculator: Calculator) extends AbstractController(cc) {

  def sum = Action(parse.json) { request =>
    //Validate llama el método reads.
    val result = request.body.validate[CalculatorInData]
    //Método fold para proceso exitoso y fallido.
    result.fold(
      errors => {
        BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      calculatorInDate => {
        val answer = calculator.sum(calculatorInDate.value1, calculatorInDate.value2)
        Ok(Json.obj("status" -> "OK", "message" -> (answer)))
      })
  }

  def substract = Action(parse.json) { request =>
    val placeResult = request.body.validate[CalculatorInData]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      calculatorInDate => {
        val answer = calculator.substract(calculatorInDate.value1, calculatorInDate.value2)
        Ok(Json.obj("status" -> "OK", "message" -> (answer)))
      })
  }

  def multiply = Action(parse.json) { request =>
    val placeResult = request.body.validate[CalculatorInData]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      calculatorInDate => {
        val answer = calculator.multiply(calculatorInDate.value1, calculatorInDate.value2)
        Ok(Json.obj("status" -> "OK", "message" -> (answer)))
      })
  }

  def divide = Action(parse.json) { request =>
    val placeResult = request.body.validate[CalculatorInData]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      calculatorInDate => {
        val answer = calculator.divide(calculatorInDate.value1, calculatorInDate.value2)
        Ok(Json.obj("status" -> "OK", "message" -> (answer)))
      })
  }

}