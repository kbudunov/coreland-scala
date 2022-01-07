package com.kbudunov.scala.core.fp.monad.either

object EitherMatching extends App {
  //matching
  val result: Any = foo(3) match {
    case Left(msg)  => msg
    case Right(msg) => msg
  }

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a + 1)
  }
}
