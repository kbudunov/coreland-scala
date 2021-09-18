package com.kbudunov.lesson.core.classes

class Objects {
  val firstBar = new Bar("first")
  val secondBar: Bar = Bar("second")

  Timer.currentCount()
}

case class Bar(foo: String)

object Bar {
  def apply(foo: String) = new Bar(foo)
}

object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}
