package actions

import javax.inject._
import play.api.mvc._
import scala.concurrent.Future
import play.api.Logger
import scala.concurrent.ExecutionContext

class LoggingAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    Logger.info("Calling action")
    block(request)
  }
}