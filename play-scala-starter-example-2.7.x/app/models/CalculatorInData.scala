package models

import play.api.libs.json._

case class CalculatorInData(val value1: Int, val value2: Int)

object CalculatorInData {

  //Convierte de objeto a json.Mapeo automatico. Escritura.
  //implicit val inWrites = Json.writes[CalculatorInData]
  implicit val inWrites = new Writes[CalculatorInData] {
    def writes(data: CalculatorInData) = Json.obj(
      "value1" -> data.value1,
      "value2" -> data.value2)
  }

  //Json a objeto.Lectura.
  implicit val inReads = Json.reads[CalculatorInData]

  //En caso de no venir parametros los coloca por defecto.Evita errores.
  implicit val inFormat = Json.using[Json.WithDefaultValues].format[CalculatorInData]

}