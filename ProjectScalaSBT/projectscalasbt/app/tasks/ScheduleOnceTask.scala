package tasks

import javax.inject.{Inject}
import akka.actor.{ActorSystem}
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class ScheduleOnceTask @Inject() (actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {

  actorSystem.scheduler.scheduleOnce(delay = 10.seconds) {
    // the block of code that will be executed
    print("Executing something... ScheduleOnceTask")
  }

}