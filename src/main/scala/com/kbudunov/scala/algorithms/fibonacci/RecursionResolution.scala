package com.kbudunov.scala.algorithms.fibonacci

object RecursionResolution extends App {

  println(calculate(4))

  def calculate(x: Int): Int = x match {
    case 0 | 1 => x
    case _ =>
      calculate(x - 1) + calculate(x - 2)
  }

}
