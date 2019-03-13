package repositories

import javax.inject.{ Inject, Singleton }
import models.Person
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }
import java.sql.Date
import java.time.LocalDate
import models.Person

@Singleton
class PersonRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  class DateUtil {
    implicit val localDateColumnType = MappedColumnType.base[LocalDate, Date](
      ldt => Date.valueOf(ldt),
      t => t.toLocalDate())
  }

  private class PersonEntity(tag: Tag) extends Table[Person](tag, "PERSON") {

    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def lastName = column[String]("LASTNAME")
    def identification = column[Int]("IDENTIFICATION")
    def identificationType = column[String]("IDENTIFICATIONTYPE")
    def age = column[Int]("AGE")
    def birthDay = column[LocalDate]("BIRTHDAY")

    /*def identificationTypeId = column[Long]("IDENTIFICATIONTYPE_ID")
    def identificationTypeFk = foreignKey("IDENTIFICATIONTYPE_ID", identificationTypeId, IdentificationType)(_.id)*/

    def * = (id, name, lastName, identification, identificationType, age, birthDay) <> ((Person.apply _).tupled, Person.unapply)
  }

  private val peopleTableQuery = TableQuery[PersonEntity]

  //def insert(person: Person): Future[Unit] = db.run(peopleTableQuery += person).map { _ => () }
  def insert(person: Person): Future[Person] = db.run {
    // create a projection of just the document and name. no insert a value for the id column
    (peopleTableQuery.map(p => (p.name, p.lastName, p.identification, p.identificationType, p.age, p.birthDay))
      //return id of new person
      returning peopleTableQuery.map(_.id)
      //work with data and id, and finally insert the person into database
      into ((propData, id) => Person(id, propData._1, propData._2, propData._3, propData._4, propData._5, propData._6))) += (person.name, person.lastName, person.identification, person.identificationType, person.age, person.birthDay)
  }

  def update(person: Person): Future[Int] = db.run(peopleTableQuery.filter(_.id === person.id).update(person))

  def upsert(person: Person): Future[Int] = db.run(peopleTableQuery.insertOrUpdate(person))

  //1er elemento statements.head
  def delete(person: Person): Future[Int] = db.run(peopleTableQuery.filter(_.id === person.id).delete)

  def findById(id: Long): Future[Option[Person]] = db.run(peopleTableQuery.filter(_.id === id).result.headOption)

  def all(): Future[Seq[Person]] = db.run(peopleTableQuery.result)
}