package controllers

import play.api.mvc._
import akka.actor._
import javax.inject._
import actors.HelloActor

import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ActorController @Inject() (system: ActorSystem, cc: ControllerComponents)
  extends AbstractController(cc) {

  //val helloActor = system.actorOf(HelloActor.props, "hello-actor")
  val helloActor = ActorSystem().actorOf(Props[HelloActor])

  

  /* def sayHello(name: String) = Action.async {
    (helloActor ? SayHello(name)).mapTo[String].map { message =>
      Ok(message)
    }
  }*/

  def sayHello(name: String) = Action.async {    
    implicit val timeout: Timeout = 5.seconds
    (helloActor ? HelloActor.SayHello(name)).mapTo[String].map { message =>
      Ok(message)
    }
  }

}