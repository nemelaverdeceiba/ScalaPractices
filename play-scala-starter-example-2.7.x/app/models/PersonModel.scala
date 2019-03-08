
package models

import play.api.libs.json._

case class PersonModel(id: Long, name: String, age: Int)

object PersonModel {
  //Convierte de objeto a json.Mapeo automatico. Escritura.
  implicit val inWrites = Json.writes[PersonModel]

  /* implicit val inWrites = new Writes[PersonModel] {
    def writes(data: PersonModel) = Json.obj(
      "id" -> data.id,
      "name" -> data.name,
      "age" -> data.age)
  }*/

  //Json a objeto.Lectura.
  implicit val inReads = Json.reads[PersonModel]

  //En caso de no venir parametros los coloca por defecto.Evita errores.
  implicit val inFormat = Json.using[Json.WithDefaultValues].format[PersonModel]

  var list: List[PersonModel] = {
    List(
      PersonModel(
        1, "Nelson", 27),
      PersonModel(
        2, "Dario", 28),
      PersonModel(
        3, "Gustavo Adolfo", 29),
      PersonModel(
        4, "Jhon Edison", 30),
      PersonModel(
        5, "Juan Esteban", 31))
  }
}