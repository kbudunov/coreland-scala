package com.kbudunov.scala.core.fp.functions

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("Hello", 3))

  def aFunction(): Int = 23
  println(aFunction)

  //WHEN YOU NEED LOOPS, USE RECURSION (main idea in FP)
  def aRepeatedFunction(s: String, n: Int): String = {
    if (n == 1) s
    else s + aRepeatedFunction(s, n - 1)
  }
  println(aRepeatedFunction("hello", 3))

  //functions in functions
  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b
    aSmallFunction(n, n - 1)
  }

  //Factorial
  //1
  def factorialRec(x: Int): Int =
    x match {
      case 1 => 1
      case _ => x * factorialRec(x - 1)
    }

  //2
  def factorialTailRec(x: Int): Int = {
    @scala.annotation.tailrec
    def accFactorial(x: Int, acc: Int = 1): Int = {
      x match {
        case 1 => acc
        case _ => accFactorial(x - 1, acc * x)
      }
    }
    accFactorial(x)
  }

  //3
  def factorialFold(x: Int): Int = (1 to x).foldLeft(1)(_ * _)

  //4
  def factorialReduce(x: Int): Int = (1 to x).reduce(_ * _)

  //Fibonachi
  //1
  def fibonachiRec(x: Int): Int = {
    x match {
      case 0 | 1 => x
      case _     => fibonachiRec(x - 1) + fibonachiRec(x - 2)
    }
  }

  //2
  def fibonachiTailRec(x: Int): Int = {
    @scala.annotation.tailrec
    def fibonachi(x: Int, a: Int = 0, b: Int = 1): Int = {
      x match {
        case 0 => a
        case _ => fibonachi(x - 1, b, a + b)
      }
    }
    fibonachi(x)
  }

  println(fibonachiTailRec(8))

//  0 0
//  1 1
//  2 1
//  3 2
//  4 3
//  5 5
//  6 8
//  7 13
//  8 21

  def isPrime(x: Int): Boolean = {
    @scala.annotation.tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else x % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(x / 2)
  }

  println(isPrime(37))
  println(isPrime(37 * 17))

}
