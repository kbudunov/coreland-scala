package com.kbudunov.lesson.core.function

object Currying extends App {

  def multiply(m: Int)(n: Int): Int = m * n

  val result = multiply(3)(2)

  val timesTwo: Int => Int = multiply(2) _
  timesTwo(3)

}