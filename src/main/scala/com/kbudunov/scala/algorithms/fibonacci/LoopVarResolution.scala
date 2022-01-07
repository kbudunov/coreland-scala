package com.kbudunov.scala.algorithms.fibonacci

object LoopVarResolution extends App {
  println(fib(4))

  def fib(x: Int): Int = {
    var a = 0
    var b = 1
    var r = 0

    while (r < x) {
      val c = b + a
      b = a
      a = c
      r = r + 1
    }
    a
  }
}
