package com.playground.springbootscala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootScalaApplication {// must declare the class, object is a final class which prevent running .run() function
  // the worst disadvantage of using Scala is intellij only shows Scala types in auto-complete by default,
  // to show Java type, either press ctrl+space again, or finish the typing
}

object SpringBootScalaApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringBootScalaApplication], args:_*) //don't use this.type, we want the class not the object
  }
}



