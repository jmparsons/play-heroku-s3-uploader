package controllers

import play.api._
import play.api.mvc._

import play.api.Play._

import fly.play.s3._
import play.api.libs.concurrent._
import play.api.libs.concurrent.Execution.Implicits._

import org.apache.commons.io._

object Application extends Controller {

  val bucketName = Play.application.configuration.getString("my.s3bucket").get
  val bucket = S3(bucketName)
  val bucketDir = "uploads/"

  def index = Action { implicit request =>
    val result = bucket.list(bucketDir)
    Async {
      result.map {
        case Left(error) => throw new Exception("Error: " + error.toString)
        case Right(list) => Ok(views.html.index(list, bucketName))
      }
    }
  }

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("file").map { file =>
      import java.net.URLEncoder.encode
      val filename = encode(file.filename, "UTF-8").replace("+", "-") //just basic sanitization
      val result = bucket + BucketFile(bucketDir + filename, file.contentType.get, FileUtils.readFileToByteArray(file.ref.file))
      Async {
        result.map {
          case Left(error) => throw new Exception("Error: " + error.toString)
          case Right(list) => Redirect(routes.Application.index)
        }
      }
    }.getOrElse {
      Redirect(routes.Application.index).flashing(
        "error" -> "Missing file"
      )
    }
  }

}