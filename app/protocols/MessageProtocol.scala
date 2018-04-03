package protocols

import play.api.libs.json.JsValue


object MessageProtocol {
  case class AddUser(user: UsersData)

  case class UsersData(email: String, password: String, comment: Option[String] = None, pLanguage: Option[String] = None, sLanguages: Option[JsValue] = None)
}
