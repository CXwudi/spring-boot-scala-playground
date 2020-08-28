package com.playground.springbootscala.services

import com.playground.springbootscala.models.UserMessageWithAllMessage


trait ReplyBackService {
  def replyBackMessage(name: String, message: String): UserMessageWithAllMessage;
}
