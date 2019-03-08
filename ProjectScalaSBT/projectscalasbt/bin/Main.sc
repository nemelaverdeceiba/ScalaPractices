import play.api.libs.json._

object Main {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // basic types
val jsonString = Json.toJson("Fiver")             //> jsonString  : play.api.libs.json.JsValue = "Fiver"
val jsonNumber = Json.toJson(4)                   //> jsonNumber  : play.api.libs.json.JsValue = 4
val jsonBoolean = Json.toJson(false)              //> jsonBoolean  : play.api.libs.json.JsValue = false

// collections of basic types
val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4))//> jsonArrayOfInts  : play.api.libs.json.JsValue = [1,2,3,4]
val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"))
                                                  //> jsonArrayOfStrings  : play.api.libs.json.JsValue = ["Fiver","Bigwig"]
    
    
    //Propios JSON CONVERTS con writes
    
    case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])


import play.api.libs.json._

implicit val locationWrites = new Writes[Location] {
  def writes(location: Location) = Json.obj(
    "lat" -> location.lat,
    "long" -> location.long
  )
}                                                 //> locationWrites  : play.api.libs.json.Writes[Main.Location]{def writes(locati
                                                  //| on: Main.Location): play.api.libs.json.JsObject} = Main$$anon$1@5a955565

implicit val residentWrites = new Writes[Resident] {
  def writes(resident: Resident) = Json.obj(
    "name" -> resident.name,
    "age" -> resident.age,
    "role" -> resident.role
  )
}                                                 //> residentWrites  : play.api.libs.json.Writes[Main.Resident]{def writes(reside
                                                  //| nt: Main.Resident): play.api.libs.json.JsObject} = Main$$anon$2@6293abcc

implicit val placeWrites = new Writes[Place] {
  def writes(place: Place) = Json.obj(
    "name" -> place.name,
    "location" -> place.location,
    "residents" -> place.residents
  )
}                                                 //> placeWrites  : play.api.libs.json.Writes[Main.Place]{def writes(place: Main
                                                  //| .Place): play.api.libs.json.JsObject} = Main$$anon$3@7995092a

val place = Place(
  "Watership Down",
  Location(51.235685, -1.309197),
  Seq(
    Resident("Fiver", 4, None),
    Resident("Bigwig", 6, Some("Owsla"))
  )
)                                                 //> place  : Main.Place = Place(Watership Down,Location(51.235685,-1.309197),Li
                                                  //| st(Resident(Fiver,4,None), Resident(Bigwig,6,Some(Owsla))))

val json = Json.toJson(place)                     //> json  : play.api.libs.json.JsValue = {"name":"Watership Down","location":{"
                                                  //| lat":51.235685,"long":-1.309197},"residents":[{"name":"Fiver","age":4,"role
                                                  //| ":null},{"name":"Bigwig","age":6,"role":"Owsla"}]}
 
 
 //Obtener valor de json
 
 val lat = (json \ "location" \ "lat").get        //> lat  : play.api.libs.json.JsValue = 51.235685
val bigwig = (json \ "residents" \ 1).get         //> bigwig  : play.api.libs.json.JsValue = {"name":"Bigwig","age":6,"role":"Ows
                                                  //| la"}
val names = json \\ "name"                        //> names  : Seq[play.api.libs.json.JsValue] = List("Watership Down", "Fiver", 
                                                  //| "Bigwig")
                                                  
                                             
 

}