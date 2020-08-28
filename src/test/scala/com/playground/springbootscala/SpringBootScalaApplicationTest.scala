package com.playground.springbootscala

import com.typesafe.scalalogging.Logger
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.TestContextManager

@SpringBootTest
class SpringBootScalaApplicationTest extends AnyFunSpec with Matchers {
  private val log = Logger[this.type]

  @Autowired
  private val context: ConfigurableApplicationContext = null
  new TestContextManager(this getClass).prepareTestInstance(this)

  describe("spring initialization"){
    it("should start a spring boot context when test run"){
      123 shouldBe 123
      log.info(s"test pass")
    }
  }

}
