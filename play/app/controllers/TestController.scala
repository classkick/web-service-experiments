package controllers

import javax.inject.Inject

import model.TestClass
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import services._

import scala.concurrent.ExecutionContext
import scala.util.Random

class TestController @Inject() (service: TestService, json: JsonService)
                               (implicit ec: ExecutionContext) extends Controller {

    def map = Action { implicit request =>
        Ok(json.toJson(
            Map(
                ("value", "hello from play"),
                ("value2", (5, 2, 3, 1))
            )
        ))
    }

    def getObject = Action { implicit request =>
        Ok("hello world")

        //json.toJson(new TestClass("value 1", service.something))
    }

    def postObject = Action { implicit request =>

        service.something = Random.alphanumeric.take(15).mkString

        Ok(json.toJson(new TestClass("value 1", service.something)))
    }

}