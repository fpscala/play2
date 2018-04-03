package dao

import javax.inject.{Inject, Singleton}
import com.google.inject.ImplementedBy
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import com.typesafe.scalalogging.LazyLogging
import protocols.MessageProtocol.UsersData
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

trait UsersComponent  { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import utils.PostgresDriver.api._

  class Users(tag: Tag) extends Table[UsersData](tag, "users") {
    def email = column[String]("email", O.PrimaryKey)
    def password = column[String]("password")
//    def comment = column[Option[String]]("comment")
//    def sLanguages = column[List[String]]("sLanguages")
//    def pLanguage = column[String]("pLanguage")

    def * = (email, password) <> (UsersData.tupled, UsersData.unapply _)
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
  import scala.concurrent.ExecutionContext.Implicits.global

  val users = TableQuery[Users]

  override def create(userData: UsersData): Future[String] = {
    db.run {
      (users returning users.map(_.email)) += userData
    }
  }
}
