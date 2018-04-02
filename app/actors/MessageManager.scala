package actors

import akka.actor.{Actor, ActorLogging}
import akka.util.Timeout
import javax.inject.Inject
import play.api.Environment
import protocols.MessageProtocol.GetMessage

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

class MessageManager @Inject()(val environment: Environment)
                              (implicit val ec: ExecutionContext)
  extends Actor with ActorLogging{

  implicit val defaultTimeout = Timeout(60.seconds)

  def receive = {
    case GetMessage(msg: String) =>
      log.info(s"msg from client: $msg")
      sender() ! s"hello, $msg"
    case _ => log.info(s"received unknown message")
  }
}
