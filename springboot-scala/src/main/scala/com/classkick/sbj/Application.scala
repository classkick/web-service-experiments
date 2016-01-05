package com.classkick.sbj

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MainConfig

object Application {
    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[MainConfig], args:_*)
    }
}
