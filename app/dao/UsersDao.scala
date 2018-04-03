package dao

import javax.inject.{Inject, Singleton}
import com.google.inject.ImplementedBy
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.JsValue
import protocols.MessageProtocol.UsersData
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

trait UsersComponent  { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import utils.PostgresDriver.api._

  class Users(tag: Tag) extends Table[UsersData](tag, "users") {
    def email = column[String]("email", O.PrimaryKey)
    def password = column[String]("password")
    def comment = column[String]("comment")
    def pLanguage = column[String]("pLanguage")
    def sLanguages = column[JsValue]("sLanguages")

    def * = (email, password, comment.?, pLanguage.?, sLanguages.?) <> (UsersData.tupled, UsersData.unapply _)
  }
}

@ImplementedBy(classOf[UsersDaoImpl])
trait UsersDao {
  def create(usersData: UsersData): Future[String]
}

@Singleton
class UsersDaoImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends UsersDao
    with UsersComponent
    with HasDatabaseConfigProvider[JdbcProfile]
    with LazyLogging {

  import utils.PostgresDriver.api._

  val users = TableQuery[Users]

  override def create(userData: UsersData): Future[String] = {
    db.run {
      (users returning users.map(_.email)) += userData
    }
  }
}
