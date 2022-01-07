package com.kbudunov.scala.core.basics

object DefaultAndNamedArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = //default values acc: Int = 1
    n match {
      case 0 | 1 => acc
      case _     => trFact(n - 1, n * acc)
    }

  def trFact2(n: Int = 2, acc: Int = 1): Int = //default values acc: Int = 1
    n match {
      case 0 | 1 => acc
      case _     => trFact(n - 1, n * acc)
    }

  trFact2(acc = 5, n = 3)

}
