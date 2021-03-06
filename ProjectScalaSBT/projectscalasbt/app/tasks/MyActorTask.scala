package tasks

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class MyActorTask @Inject() (actorSystem: ActorSystem, @Named("HelloActor") someActor: ActorRef)(implicit executionContext: ExecutionContext) {

  actorSystem.scheduler.schedule(
    initialDelay = 0.microseconds,
    interval = 30.seconds,
    receiver = someActor,
    message = "tick"
  )

}