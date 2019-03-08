package services

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }
import models.PersonModel
import java.util.Optional

@Singleton class Person() {

  def getPersonById(idParam: Long): Option[PersonModel] = {
    //PersonModel.list.filter(_.id == idParam)(0)
    PersonModel.list.find(_.id == idParam)
  }

  def listPeopleByName(nameParam: String): List[PersonModel] = {
    PersonModel.list.filter(_.name.toUpperCase().startsWith(nameParam.toUpperCase()))
  }

}