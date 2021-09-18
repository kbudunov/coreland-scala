package com.kbudunov.lesson.algorithm.fibonacci

object TailRecResolution extends App {

  println(fib3(4))

  def fib3(n: Int): Int = {

    @scala.annotation.tailrec
    def fib_tail(n: Int, a: Int, b: Int): Int = n match {
      case 0 => a
      case _ => fib_tail(n - 1, b, a + b)
    }
    fib_tail(n, 0, 1)
  }
}
