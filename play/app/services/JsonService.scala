package services

import javax.inject.Singleton

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@Singleton
class JsonService {

    val mapper: ObjectMapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)

    def toJson[T](value: AnyRef): String = {
        mapper.writeValueAsString(value)
    }

    def fromJson[T](value: String)(implicit m: Manifest[T]): T = {
        mapper.readValue(value, m.runtimeClass.asInstanceOf[Class[T]])
    }
}
