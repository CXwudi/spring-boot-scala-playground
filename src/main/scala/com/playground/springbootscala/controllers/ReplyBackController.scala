package com.playground.springbootscala.controllers


import com.playground.springbootscala.managers.CounterLike
import com.playground.springbootscala.models.UserMessageWithAllMessage
import com.playground.springbootscala.services.ReplyBackService
import org.apache.commons.lang3.StringUtils
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}

@RestController
class ReplyBackController(private val replyBackService: ReplyBackService with CounterLike) {
  private val log = com.typesafe.scalalogging.Logger[this.type]

  @RequestMapping(method = Array(RequestMethod.GET), path = Array("/", "/messaging"))
  def replyBackToUser(
    @RequestParam(value = "name", defaultValue = "User") name: String = "User",
    @RequestParam message: String): UserMessageWithAllMessage = {
    // making new message
    val messageNew = if (StringUtils.isBlank(message)){
      "No message"
    } else message

    log.info(s"name = $name, message = $messageNew")
    val userMsg = replyBackService.replyBackMessage(name, messageNew)
    log.info(s"count = ${replyBackService.getCurrentCount}")
    userMsg
  }

}
