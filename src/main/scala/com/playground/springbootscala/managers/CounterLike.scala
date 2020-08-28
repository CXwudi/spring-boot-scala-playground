package com.playground.springbootscala.managers

trait CounterLike {
  def getAndIncrement: Int
  def getCurrentCount: Int
}
