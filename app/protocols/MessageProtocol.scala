package protocols

object MessageProtocol {
  case class GetMessage(msg: String)

  case class UsersData(email: String, password: String)
}
