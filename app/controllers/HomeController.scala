package controllers

import javax.inject._

import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.Json
import play.api.mvc._
import views.html.forms._

import scala.concurrent.Future


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               indexTemplate: index)
  extends BaseController with LazyLogging {

  def index = Action {
    Ok(indexTemplate())
  }

  def register() = Action.async(parse.json){ implicit request => {
    val email = (request.body \ "email").as[String]
    Future.successful(Ok(Json.toJson(s"You successfully added email $email")))
  }}

}
