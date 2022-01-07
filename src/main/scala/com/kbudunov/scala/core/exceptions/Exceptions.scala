package com.kbudunov.scala.core.exceptions

object Exceptions extends App {

  val result: AnyVal = try {
    getInt(true)
  } catch {
    case e: IllegalArgumentException => println(e.printStackTrace())
    case _: RuntimeException         => println("caught a Runtime exeption")
  } finally {
    println("no matter what!")
  }

  println(result)

  class MyException extends Exception
  val exception = new MyException

  //OOM throw example
//val array = Array.ofDim(Int.MaxValue)

  //StackOverflow throw example
  def infinite: Int = 1 + infinite
//val noLimit = infinite

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No") //throw
    else 21
  }
}
