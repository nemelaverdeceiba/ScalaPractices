package models

import java.time.LocalDate
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.validation.ValidationError
import play.api.libs.json.Reads._

//case class IdentificationType(id: Long, name: String)

//case class Person(id: Long, name: String, lastName: String, identification: Int, birthDay: LocalDate, identificationType: IdentificationType)

case class Person(id: Long, name: String, lastName: String, identification: Int, identificationType: String, age: Int)

object Person {

  //Validación personas con edad mayor a 18 años.
  //val onlyLowercase: Reads[String] = StringReads.filter(ValidationError("Solo se pueden registrar personas cuya edad sea mayor a 18 años."))(age => age>18)

  //implicit val personFormat = Json.format[Person]

  //Convierte de objeto a json.Mapeo automatico. Escritura.
  implicit val inWrites = Json.writes[Person]

  //Json a objeto.Lectura.
  //implicit val inReads = Json.reads[Person]


  implicit val inReads: Reads[Person] = (
    (JsPath \ "id").read[Long] and
    (JsPath \ "name").read[String] and
    (JsPath \ "lastName").read[String] and
    (JsPath \ "identification").read[Int] and
    (JsPath \ "identificationType").read[String] and
    (JsPath \ "age").read[Int](min(18)))(Person.apply _)

  //En caso de no venir parametros los coloca por defecto.Evita errores.
  // implicit val inFormat = Json.using[Json.WithDefaultValues].format[Person]
}
