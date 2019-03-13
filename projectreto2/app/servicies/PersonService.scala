package servicies

import javax.inject._
import models.Person
import repositories.PersonRepository
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class PersonService @Inject() (personRepository: PersonRepository)(implicit ec: ExecutionContext) {

  def insertPerson(person: Person): Future[Person] = {
    personRepository.insert(person)
  }
  def updatePerson(person: Person): Future[Int] = personRepository.update(person)
  def deletePerson(person: Person): Future[Int] = personRepository.delete(person)
  def findPersonById(id: Long): Future[Option[Person]] = personRepository.findById(id)
  def listPeople(): Future[Seq[Person]] = personRepository.all()

}