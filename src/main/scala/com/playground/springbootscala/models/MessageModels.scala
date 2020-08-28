package com.playground.springbootscala.models

import java.time.LocalDateTime

import org.eclipse.collections.api.RichIterable
import org.eclipse.collections.api.list.ImmutableList

abstract class BaseUserMessage(
  val name: String,
  val msgContext: String)

case class UserMessageWithAllMessage(
  override val name: String,
  override val msgContext: String,
  allMessages: ImmutableList[BasicUserMessage], //here we use the concrete type to avoid circular deps
  time: LocalDateTime,
  order: Int) extends BaseUserMessage(name, msgContext)

case class BasicUserMessage(
  override val name: String,
  override val msgContext: String,
  time: LocalDateTime,
  order: Int) extends BaseUserMessage(name, msgContext)

object BaseUserMessage{
  def filterToGetBasicUserMessages[
      Itr <: RichIterable[BaseUserMessage],
      ImItr >: ImmutableList[BasicUserMessage]
  ](allMsgs: Itr): ImItr = {
    allMsgs.asLazy
        .select(_.isInstanceOf[BasicUserMessage])
        .collect(_.asInstanceOf[BasicUserMessage])
        .toList.toImmutable
  }
}