package controllers

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import protocols.MessageProtocol.AddUser
import views.html.forms._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                              @Named("message-manager") val messageManager: ActorRef,
                               indexTemplate: index)
                              (implicit val ec: ExecutionContext)
  extends BaseController with LazyLogging {

  implicit val defaultTimeout = Timeout(60.seconds)

  def index = Action {
    Ok(indexTemplate())
  }

  def register() = Action.async(parse.json){ implicit request => {
    val email = (request.body \ "email").as[String]
    val password = (request.body \ "psw").as[String]
    logger.info(s"email: $email")
    logger.info(s"psw: $password")
    (messageManager ? AddUser(email, password)).mapTo[String].map { message =>
      logger.info(s"message from actor: $message")
      Ok(Json.toJson(message))
    }
  }}

}
