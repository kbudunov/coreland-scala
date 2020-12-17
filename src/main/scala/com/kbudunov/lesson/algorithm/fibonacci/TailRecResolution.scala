package com.kbudunov.lesson.algorithm.fibonacci

object TailRecResolution extends App {
  def fib3( n : Int) : Int = {
    def fib_tail( n: Int, a:Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail( n-1, b, a+b )
    }
    return fib_tail( n, 0, 1)
  }
}
