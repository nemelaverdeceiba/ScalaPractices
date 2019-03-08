package persistence

import javax.inject._
import play.api.mvc._
import scala.concurrent._
//import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
//import slick.jdbc.JdbcProfile

class Application @Inject(){
/*(
  protected val dbConfigProvider: DatabaseConfigProvider,
  cc:                             ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  def index(name: String) = Action.async { implicit request =>
    val resultingUsers: Future[Seq[User]] = db.run(Users.filter(_.name === name).result)
    resultingUsers.map(users => Ok(views.html.index(users)))
  }*/
}