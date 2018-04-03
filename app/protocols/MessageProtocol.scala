package protocols

object MessageProtocol {
  case class AddUser(email: String, password: String)

  case class UsersData(email: String, password: String)
}
