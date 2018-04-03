package controllers

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import protocols.MessageProtocol.{AddUser, GetAllUsers, UsersData}
import views.html.forms._

import scala.concurrent.{ExecutionContext, Future}
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
    val comment = (request.body \ "comment").asOpt[String]
    val sLanguages = (request.body \ "sLanguages").toOption
    val pLanguage = (request.body \ "pLanguage").asOpt[String]
    logger.info(s"requestBody: ${request.body}")
    logger.info(s"email: $email")
    logger.info(s"psw: $password")
    logger.info(s"comment: $comment")
    logger.info(s"sLanguages: $sLanguages")
    logger.info(s"pLanguage: $pLanguage")
    (messageManager ? AddUser(UsersData(email,password, comment, pLanguage, sLanguages))).mapTo[String].map { message =>
      logger.info(s"message from actor: $message")
      Ok(Json.toJson(message))
    }
  }}

  def getAllUsers = Action.async {
    (messageManager ? GetAllUsers).mapTo[Seq[UsersData]].map { users =>
      logger.info(s"users: $users")
      Ok(Json.toJson(Map("users" -> users)))
    }

  }

}
