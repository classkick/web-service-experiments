package com.classkick.sbj.config

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

@Configuration
class SerializationConfig {

    @Bean
    def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
        val converter = new MappingJackson2HttpMessageConverter()
        converter.getObjectMapper.registerModule(DefaultScalaModule)

        val mediaTypes = asScalaBuffer(converter.getSupportedMediaTypes) :+ MediaType.TEXT_PLAIN
        converter.setSupportedMediaTypes(bufferAsJavaList(mediaTypes))

        converter
    }

}
