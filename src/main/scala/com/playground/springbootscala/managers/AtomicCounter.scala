package com.playground.springbootscala.managers

import java.util.concurrent.atomic.AtomicInteger

import com.playground.springbootscala.util.Manager

@Manager
class AtomicCounter extends CounterLike {
  private val incrementalCounter: AtomicInteger = new AtomicInteger(0)

  override def getCurrentCount: Int = incrementalCounter.get()

  override def getAndIncrement: Int = incrementalCounter.getAndIncrement()
}
