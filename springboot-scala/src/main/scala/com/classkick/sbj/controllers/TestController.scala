package com.classkick.sbj.controllers

import java.util.concurrent.Callable

import com.classkick.sbj.domain.TestClass
import com.classkick.sbj.services.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation._



@RestController
@RequestMapping(value = Array("/test"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
class TestController {

    @Autowired private val service: TestService = null

    def asCallable[T](f: () => T): Callable[T] = {
        new Callable[T]() {
            def call(): T = {
                f()
            }
        }
    }
    
    @RequestMapping(value = Array("/map"), method = Array(RequestMethod.GET))
    @ResponseBody def testMap: Map[String, AnyRef] = {
        Map(
        ("value", "hello from springboot"),
        ("value2", (5, 2, 3, 1))
        )
    }

    @RequestMapping(value = Array("/object"), method = Array(RequestMethod.GET))
    @ResponseBody def testObject: Callable[TestClass] = {

        asCallable(() => new TestClass("this is value 1", service.something))
    }

    @RequestMapping(value = Array("/object"), method = Array(RequestMethod.POST))
    @ResponseBody def testPost(@RequestBody input: TestClass): Callable[TestClass] = {

        asCallable(() => {

          service.something = input.value2
          new TestClass("this is value 1", service.something)
        })
    }
}
