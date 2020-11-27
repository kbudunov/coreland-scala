package com.kbudunov.lesson.core.function

object CurryingEither extends App {

  //Either[String, Int].fold(String => B, Int => B)
  val result: String = foo(-5).fold(error("Error: "), success(5))
  println(result)

  def foo(a: Int): Either[String, Int] = {
    if (a < 0 ) Left(s"$a < 0")
    else Right(a)
  }

  def success(a: Int)(b: Int): String = {
    (a + b).toString
  }

  def error(a: String)(s: String): String = {
    a + s
  }


}
