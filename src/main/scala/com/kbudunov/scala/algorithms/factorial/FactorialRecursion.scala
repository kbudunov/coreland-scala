package com.kbudunov.scala.algorithms.factorial

object FactorialRecursion extends App {

  //regular recursion
  def factorial(x: Int): Int = {
    x match {
      case 0 | 1 => 1
      case n     => factorial(n - 1) * n
    }
  }

  //tail recursion
  def tailFactorial(x: Int): Int = {

    @scala.annotation.tailrec
    def fibo(x: Int, acc: Int): Int = {
      x match {
        case 0 | 1 => acc
        case n     => fibo(n - 1, acc * n)
      }
    }
    fibo(x, 1)
  }

  println(tailFactorial(5))

}
