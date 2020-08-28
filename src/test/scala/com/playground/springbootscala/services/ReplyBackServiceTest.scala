package com.playground.springbootscala.services

import java.time.LocalDateTime

import com.playground.springbootscala.managers.{CounterLike, MessageCollector}
import com.playground.springbootscala.models.BasicUserMessage
import org.eclipse.collections.api.factory.Lists
import org.mockito.Mockito._
import org.mockito.{ArgumentCaptor, Mock, MockitoAnnotations}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}

class ReplyBackServiceTest extends AnyFunSpec with Matchers with BeforeAndAfterEach with BeforeAndAfterAll {

  @Mock
  private var counter: CounterLike = _

  @Mock
  private var msgCollector: MessageCollector = _

  private var replyBackService: ReplyBackService with CounterLike = _

  override protected def beforeAll(): Unit = {
    MockitoAnnotations.initMocks(this)
  }

  override protected def beforeEach(): Unit = {
    counter = mock(classOf[CounterLike])
    when (counter.getAndIncrement) thenReturn 0
    when (counter.getCurrentCount) thenReturn 1

    msgCollector = mock(classOf[MessageCollector])
    when (msgCollector.getAllMessages()) thenReturn Lists.immutable.`with`(
      BasicUserMessage("CXwudi", "ミクが好き", LocalDateTime.of(2020, 8, 31, 12, 39), 0),
      BasicUserMessage("Miku", "Fufufu 歌ってみた", LocalDateTime.of(2020, 8, 31, 23, 39), 1)
    )

    replyBackService = new ReplyBackServiceImpl(msgCollector, counter)
  }

  describe("ReplyBackServiceTest") {
    it("should replyBackMessage") {
      //given
      val name = "User 2"
      val message = "I am coming"
      val basicMsgArgCaptor: ArgumentCaptor[BasicUserMessage] = ArgumentCaptor.forClass(classOf[BasicUserMessage])

      //when
      val reply = replyBackService.replyBackMessage(name, message)

      //then
      reply.name shouldBe name
      reply.msgContext shouldBe message
      reply.order shouldBe 0
      reply.allMessages.toList should contain theSameElementsInOrderAs List(
          BasicUserMessage("CXwudi", "ミクが好き", LocalDateTime.of(2020, 8, 31, 12, 39), 0),
          BasicUserMessage("Miku", "Fufufu 歌ってみた", LocalDateTime.of(2020, 8, 31, 23, 39), 1)
      )

      verify(counter, times(1)).getAndIncrement
      verify(msgCollector, times(1)).collectMessage(basicMsgArgCaptor.capture())
      verify(msgCollector, times(1)).getAllMessages()
      verifyNoMoreInteractions(msgCollector)

      val basicMsgInMethod = basicMsgArgCaptor.getValue
      basicMsgInMethod.name shouldBe name
      basicMsgInMethod.msgContext shouldBe message
    }
  }
}
