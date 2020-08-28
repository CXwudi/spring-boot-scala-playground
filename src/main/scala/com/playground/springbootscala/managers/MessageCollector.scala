package com.playground.springbootscala.managers

import com.playground.springbootscala.models.BaseUserMessage
import org.eclipse.collections.api.list.ImmutableList

trait MessageCollector {
  def collectMessage(msg: BaseUserMessage): Unit
  def getAllMessages(): ImmutableList[BaseUserMessage]
}

