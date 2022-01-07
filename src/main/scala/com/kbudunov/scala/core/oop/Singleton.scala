package com.kbudunov.scala.core.oop

object Singleton extends App {
  val brain = Brain.getInstance
  println(brain)
}

class Brain private {
  override def toString = "This is the brain."
}

object Brain {
  private val brain = new Brain
  def getInstance: Brain = brain
}
