package com.playground.springbootscala.services
import java.time.LocalDateTime

import com.playground.springbootscala.managers.{CounterLike, MessageCollector}
import com.playground.springbootscala.models.{BaseUserMessage, BasicUserMessage, UserMessageWithAllMessage}
import org.eclipse.collections.api.list.ImmutableList
import org.springframework.stereotype.Service


@Service
class ReplyBackServiceImpl(
  private val msgCollector: MessageCollector,
  private val counter: CounterLike
) extends ReplyBackService with CounterLike {

  override def replyBackMessage(name: String, message: String): UserMessageWithAllMessage = {
    val oldCount = getAndIncrement
    val newMessage = BasicUserMessage(name, message, LocalDateTime.now(),oldCount)

    //get all msgs before adding new one
    val allBasicMsgCollected: ImmutableList[BasicUserMessage]
    = BaseUserMessage.filterToGetBasicUserMessages(msgCollector.getAllMessages())

    msgCollector.collectMessage(newMessage)

    //make and return message with all contexts
    UserMessageWithAllMessage(name, message, allBasicMsgCollected, LocalDateTime.now(),oldCount)
  }

  override def getAndIncrement: Int = counter.getAndIncrement

  override def getCurrentCount: Int = counter.getCurrentCount
}
