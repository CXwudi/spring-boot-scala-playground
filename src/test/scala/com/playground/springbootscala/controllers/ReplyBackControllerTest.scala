package com.playground.springbootscala.controllers

import java.time.LocalDateTime

import com.playground.springbootscala.managers.CounterLike
import com.playground.springbootscala.models.UserMessageWithAllMessage
import com.playground.springbootscala.services.ReplyBackService
import org.eclipse.collections.api.factory.Lists
import org.mockito.Mockito.{times, verify, when}
import org.mockito.{ArgumentCaptor, ArgumentMatchers, Mock, MockitoAnnotations}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatestplus.mockito.MockitoSugar
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ReplyBackControllerTest extends AnyFunSpec with Matchers with BeforeAndAfterEach with BeforeAndAfterAll with MockitoSugar {

  private var controller: ReplyBackController = _

  @Mock
  private var service: ReplyBackService with CounterLike = _

  private val testName = "CXwudi"

  private val testMsg = "test msg"

  override protected def beforeAll(): Unit = {
    MockitoAnnotations.initMocks(this)
  }

  override def beforeEach(): Unit = {
    trait DummyIntermedia extends ReplyBackService with CounterLike
    service = mock[DummyIntermedia]

    when (service.replyBackMessage(testName, testMsg)) thenReturn
        UserMessageWithAllMessage(testName, testMsg, Lists.immutable.empty(),
          LocalDateTime.of(2020, 8, 31, 23, 39), 0)
    when (service.replyBackMessage(testName, "No message")) thenReturn
        UserMessageWithAllMessage(testName, "No message", Lists.immutable.empty(),
          LocalDateTime.of(2020, 8, 31, 23, 39), 0)
    controller = new ReplyBackController(service)
  }

  describe("the controller"){
    it("should return 4xx if missing message arg"){
      val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

      mockMvc.perform(get("/"))
          .andExpect(status().is4xxClientError())
    }

    it("should return 200 and get message if message arg is there"){
      val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

      mockMvc.perform(get(s"/?message=$testMsg"))
          .andExpect(status().is2xxSuccessful())
    }

    it("should return 200 and get message if message arg is there and name is specificed"){
      val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

      mockMvc.perform(get(s"/?message=$testMsg&name=$testName"))
          .andExpect(status().is2xxSuccessful())
    }
  }

  describe("the controller method"){
    it("should return a msg"){
      //when
      val res = controller.replyBackToUser(testName, testMsg)
      //then
      res.name shouldBe testName
      res.msgContext shouldBe testMsg
    }

    it("should return a empty message is it's blank"){
      //given
      val msgArgCaptor: ArgumentCaptor[String] = ArgumentCaptor.forClass(classOf[String])
      //when
      val res = controller.replyBackToUser(testName, "  ")
      //then
      res.name shouldBe testName
      res.msgContext shouldBe "No message"
      verify(service, times(1)).replyBackMessage(
        ArgumentMatchers.eq(testName), ArgumentMatchers.eq("No message"))

    }
  }
}
