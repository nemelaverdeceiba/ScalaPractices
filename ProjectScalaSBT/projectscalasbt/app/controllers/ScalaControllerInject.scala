package controllers

import javax.inject.Inject

import play.api.db._
import play.api.mvc._

class ScalaControllerInject {

//@Inject()(db: Database, val controllerComponents: ControllerComponents) extends BaseController {
  
 /* def jdbcTest = Action {
    var outString = "Number is "
    val conn = db.getConnection()
    
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT 9 as testkey ")
      
      while (rs.next()) {
        outString += rs.getString("testkey")
      }
    } finally {
      conn.close()
    }
    Ok(outString)
  }*/
  
}