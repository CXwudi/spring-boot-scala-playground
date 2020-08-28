package com.playground.springbootscala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootScalaApplication {
  // the worst disadvantage of using Scala is intellij only shows Scala types in auto-complete by default,
  // to show Java type, either press ctrl+space again, or finish the typing
}

object SpringBootScalaApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringBootScalaApplication], args:_*)
  }
}


