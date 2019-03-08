package repositories

import javax.inject.{ Inject, Singleton }
import models.Person
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }
import models.Person
import models.Person
import java.sql.Date
import java.time.LocalDate
import models.Person

@Singleton
class PersonRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val People = TableQuery[PeopleTable]

  def all(): Future[Seq[Person]] = db.run(People.result)

  def insert(person: Person): Future[Unit] = db.run(People += person).map { _ => () }

  def update(person: Person): Future[Int] = db.run(People.filter(_.id === person.id).update(person))

  def upsert(person: Person): Future[Int] = db.run(People.insertOrUpdate(person))

  //1er elemento statements.head
  def delete(person: Person): Future[Int] = db.run(People.filter(_.id === person.id).delete)

  def findById(id: Long): Future[Option[Person]] = db.run(People.filter(_.id === id).result.headOption)

  private class PeopleTable(tag: Tag) extends Table[Person](tag, "PERSON") {

    def id = column[Long]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def lastName = column[String]("LASTNAME")
    def identification = column[Int]("IDENTIFICATION")
    def identificationType = column[String]("IDENTIFICATIONTYPE")
    def age = column[Int]("AGE")

    /*implicit val localDateColumnType = MappedColumnType.base[LocalDate, Date](
      ldt => Date.valueOf(ldt),
      t => t.toLocalDate())
    def birthDay = column[Date]("BIRTHDAY")*/

    /*def identificationTypeId = column[Long]("IDENTIFICATIONTYPE_ID")
    def identificationTypeFk = foreignKey("IDENTIFICATIONTYPE_ID", identificationTypeId, IdentificationType)(_.id)*/

    def * = (id, name, lastName, identification, identificationType,age) <> ((Person.apply _).tupled, Person.unapply)
  }

}