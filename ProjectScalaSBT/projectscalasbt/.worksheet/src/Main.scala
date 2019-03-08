import play.api.libs.json._

object Main {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 
  println("Welcome to the Scala worksheet");$skip(58); 
  
  // basic types
val jsonString = Json.toJson("Fiver");System.out.println("""jsonString  : play.api.libs.json.JsValue = """ + $show(jsonString ));$skip(32); 
val jsonNumber = Json.toJson(4);System.out.println("""jsonNumber  : play.api.libs.json.JsValue = """ + $show(jsonNumber ));$skip(37); 
val jsonBoolean = Json.toJson(false);System.out.println("""jsonBoolean  : play.api.libs.json.JsValue = """ + $show(jsonBoolean ));$skip(82); 

// collections of basic types
val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4));System.out.println("""jsonArrayOfInts  : play.api.libs.json.JsValue = """ + $show(jsonArrayOfInts ));$skip(62); 
val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"))
    
    
    //Propios JSON CONVERTS con writes
    
    case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])


import play.api.libs.json._;System.out.println("""jsonArrayOfStrings  : play.api.libs.json.JsValue = """ + $show(jsonArrayOfStrings ));$skip(438); 

implicit val locationWrites = new Writes[Location] {
  def writes(location: Location) = Json.obj(
    "lat" -> location.lat,
    "long" -> location.long
  )
};System.out.println("""locationWrites  : play.api.libs.json.Writes[Main.Location]{def writes(location: Main.Location): play.api.libs.json.JsObject} = """ + $show(locationWrites ));$skip(189); 

implicit val residentWrites = new Writes[Resident] {
  def writes(resident: Resident) = Json.obj(
    "name" -> resident.name,
    "age" -> resident.age,
    "role" -> resident.role
  )
};System.out.println("""residentWrites  : play.api.libs.json.Writes[Main.Resident]{def writes(resident: Main.Resident): play.api.libs.json.JsObject} = """ + $show(residentWrites ));$skip(188); 

implicit val placeWrites = new Writes[Place] {
  def writes(place: Place) = Json.obj(
    "name" -> place.name,
    "location" -> place.location,
    "residents" -> place.residents
  )
};System.out.println("""placeWrites  : play.api.libs.json.Writes[Main.Place]{def writes(place: Main.Place): play.api.libs.json.JsObject} = """ + $show(placeWrites ));$skip(160); 

val place = Place(
  "Watership Down",
  Location(51.235685, -1.309197),
  Seq(
    Resident("Fiver", 4, None),
    Resident("Bigwig", 6, Some("Owsla"))
  )
);System.out.println("""place  : Main.Place = """ + $show(place ));$skip(31); 

val json = Json.toJson(place);System.out.println("""json  : play.api.libs.json.JsValue = """ + $show(json ));$skip(74); 
 
 
 //Obtener valor de json
 
 val lat = (json \ "location" \ "lat").get;System.out.println("""lat  : play.api.libs.json.JsValue = """ + $show(lat ));$skip(42); 
val bigwig = (json \ "residents" \ 1).get;System.out.println("""bigwig  : play.api.libs.json.JsValue = """ + $show(bigwig ));$skip(27); 
val names = json \\ "name";System.out.println("""names  : Seq[play.api.libs.json.JsValue] = """ + $show(names ))}
                                                  
                                             
 

}
