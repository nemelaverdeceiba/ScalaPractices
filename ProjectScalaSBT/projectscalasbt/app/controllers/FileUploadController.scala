package controllers

import javax.inject._
import play.api.mvc._
import java.nio.file.Paths

@Singleton
class FileUploadController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>

      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename = Paths.get(picture.filename).getFileName
      print(filename)
      //picture.ref.moveTo(Paths.get(s"/tmp/picture/$filename"), replace = true)
      //moveTo deprecado.
      picture.ref.moveFileTo(Paths.get(s"D:/tmp/$filename"), replace = true)

      Ok("File uploaded")
    }.getOrElse {
      Ok("error")
      // Redirect(routes.FileUploadController.index).flashing(
      //    "error" -> "Missing file")
    }
  }

}