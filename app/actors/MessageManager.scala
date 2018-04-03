package actors

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe
import akka.util.Timeout
import dao.UsersDao
import javax.inject.Inject
import play.api.Environment
import protocols.MessageProtocol.{AddUser, UsersData}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}

class MessageManager @Inject()(val environment: Environment,
                               usersDao: UsersDao)
                              (implicit val ec: ExecutionContext)
  extends Actor with ActorLogging{

  implicit val defaultTimeout = Timeout(60.seconds)

  def receive = {
    case AddUser(user) =>
      addUser(user).pipeTo(sender())

    case _ => log.info(s"received unknown message")
  }

  private def addUser(userD: UsersData) = {
    usersDao.create(userD).flatMap {user =>
      Future.successful(user)
    }
  }
}
