package com.kbudunov.lesson.algorithm.fibonacci

object LoopVarResolution extends App {
  println(fib(5))

  def fib(x: Int): Int = {
    var a = 0
    var b = 1
    var r = 0

    while (r < x) {
      val c = a + b
      a = b
      b = c
      r = r + 1
    }
    a
  }
}
