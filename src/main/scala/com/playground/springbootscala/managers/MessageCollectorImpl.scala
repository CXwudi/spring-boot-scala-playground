package com.playground.springbootscala.managers

import com.playground.springbootscala.models.BaseUserMessage
import com.playground.springbootscala.util.Manager
import org.eclipse.collections.api.factory.Lists
import org.eclipse.collections.api.list.{ImmutableList, MutableList}

@Manager
class MessageCollectorImpl extends MessageCollector {
  private val msgList: MutableList[BaseUserMessage] = Lists.mutable.empty()

  override def collectMessage(msg: BaseUserMessage): Unit = msgList.add(msg)

  override def getAllMessages(): ImmutableList[BaseUserMessage] = msgList.toImmutable
}
