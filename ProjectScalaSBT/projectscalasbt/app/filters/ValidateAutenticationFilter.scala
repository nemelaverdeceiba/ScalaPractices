package filters

import play.api.mvc._
import scala.concurrent.Future
import play.api.Logger
import scala.concurrent.ExecutionContext


class ValidateAutenticationFilter{
 // extends ActionBuilderImpl[Request]
  //with ActionFilter[Request] {

  /*def filter[A](request: Request[A]): Future[Option[Result]] = {
    val result = request.headers.get("X-Forwarded-Proto") match {
      case Some(proto) if proto == "https" => None
      case _                               => Some(Results.Forbidden)
    }
    Future.successful(result)
  }

  def ValidateAutenticationAction(implicit ec: ExecutionContext) = new ActionFilter[ItemRequest] {
    def executionContext = ec
    def filter[A](input: ItemRequest[A]) = Future.successful {
      if (!input.item.accessibleByUser(input.username))
        Some(Forbidden)
      else
        None
    }
  }*/

}