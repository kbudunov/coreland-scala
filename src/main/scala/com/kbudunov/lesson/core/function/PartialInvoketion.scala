package com.kbudunov.lesson.core.function


object PartialInvoketion extends App {

  def adder(m: Int, n: Int): Int = m + n
  val add2 = adder(2, _: Int)
  println(add2(3))
}
