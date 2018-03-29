package controllers

import javax.inject._

import com.typesafe.scalalogging.LazyLogging
import play.api.mvc._


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)
  extends BaseController with LazyLogging {

  def index = Action {
    logger.info(s"asdfdfddd")
    Ok(views.html.index("Your new application is ready."))
  }

  def homeWork = Action {
    Ok(views.html.home_works())
  }

  def register() = Action { implicit request => {
    val rr = request.body
//    val rr = (request.body \ "email").as[String]
    logger.info(s"rr: $rr")
    Ok("asd")
  }}

}
