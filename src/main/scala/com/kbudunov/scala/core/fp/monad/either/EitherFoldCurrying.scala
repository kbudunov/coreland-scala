package com.kbudunov.scala.core.fp.monad.either

object EitherFoldCurrying extends App {

  //Either[String, Int].fold(String => B, Int => B)

  val f1: String => String = error("Error: ")
  val f2: Int => String = success(5)

  val result: String = foo(-5).fold(error("Error: "), success(5)) //f1 and f2
  println(result)

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a)
  }

  def success(a: Int)(b: Int): String = {
    (a + b).toString
  }

  def error(a: String)(s: String): String = {
    println("++++" + a)
    a + s
  }
}
