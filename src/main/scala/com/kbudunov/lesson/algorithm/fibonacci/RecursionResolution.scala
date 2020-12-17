package com.kbudunov.lesson.algorithm.fibonacci

object RecursionResolution extends App {

  println(calculate(5))

  def calculate(x: Int): Int = x match {
    case 0 | 1 => x
    case _     => calculate(x - 1) + calculate(x - 2)
  }

}
