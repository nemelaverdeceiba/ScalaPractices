package com.ceiba.units

import org.scalatest._
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import controllers.PersonController
import models.Person
import scala.concurrent._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.test.FakeRequest

class PersonUnitSpec extends PlaySpec with MockitoSugar {

  "PersonController" should {

     val mockDataService = mock[PersonController]
    
    
  /*  "return status OK and text Persona creada de manera exitosa. If person is created" in {
      when(mockDataService.insertPerson()) thenReturn  "Persona creada de manera exitosa."
    }*/
  

   /* "return the people list" in {     
      when(mockDataService.listPeople()) thenReturn Future(Seq(Person(1, "", "", 0, "", 28)))
      
    }*/

  }

}