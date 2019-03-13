package com.ceiba.units

import org.scalatest._
import org.scalamock.scalatest._
import repositories.PersonRepository
import servicies.PersonService
import org.scalatestplus.play._
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import scala.concurrent._
import models.Person
import scala.util.Success
import scala.util.Failure
import java.time.LocalDate

class PersonUnitsSpec extends PlaySpec with GuiceOneAppPerTest with MockFactory {

  "PersonService" should {

    val personRepository = mock[PersonRepository]
    val personService = new PersonService(personRepository)

    "return a valid result with insertPerson" in {
      val person = Person(1, "Nelson", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13))

      (personRepository.insert _).expects(person).returning(Future(Person(1, "Nelson", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13))))

      val personReturn = personService.insertPerson(person)
      personReturn.onComplete {
        case Success(data) => data.name mustBe ("Nelson")
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }

    }

    "return a valid result with updatePerson" in {

      val person = Person(1, "NelsonEditado", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13))

      (personRepository.update _).expects(person).returning(Future.apply(1))

      val personReturn = personService.updatePerson(person)
      personReturn.onComplete {
        case Success(data) => data mustBe 1
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }

    }

    "return a valid person with findPersonById" in {
      val person = Person(1, "NelsonEditado", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13))

      (personRepository.findById _).expects(1).returning(Future.apply(Some(person)))

      val personReturn = personService.findPersonById(1)
      personReturn.onComplete {
        //case Success(data) => data.nonEmpty mustBe true
        case Success(data) => data.get.name mustBe "NelsonEditado"
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }
    }

    "return None when no found person by id with findPersonById" in {
      val person = Person(10, "", "", 0, "", 28, LocalDate.of(1991, 3, 13))

      (personRepository.findById _).expects(10).returning(Future.apply(None))

      val personReturn = personService.findPersonById(10)
      personReturn.onComplete {
        case Success(data) => data.isEmpty mustBe true
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }
    }

    //Test Listado de personas.
    "return a valid List with listPeople" in {

      (personRepository.all _).expects().returning(Future(Seq(Person(1, "Nelson", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13)))))

      val people = personService.listPeople()
      people.onComplete {
        case Success(data) => data.length mustBe (1)
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }

    }

    "return a valid result with deletePerson" in {
      val person = Person(1, "Nelson", "Mejia", 1094918697, "Cedula", 28, LocalDate.of(1991, 3, 13))

      (personRepository.delete _).expects(person).returning(Future.apply(1))

      val personReturn = personService.deletePerson(person)
      personReturn.onComplete {
        case Success(data) => data mustBe 1
        case Failure(t)    => println("An error has occurred: " + t.getMessage)
      }
    }

  }
}