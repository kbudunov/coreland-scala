package com.kbudunov.lesson.core.classes

object Singleton extends App {
  val brain = Brain.getInstance
  println(brain)
}

class Brain private {
  override def toString = "This is the brain."
}
object Brain {
  val brain = new Brain
  def getInstance = brain
}