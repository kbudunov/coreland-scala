package com.kbudunov.scala.core.fp.monad.either

object EitherFold extends App {

  val value: Either[String, Int] = foo(-3)

  val f: String => String = s => s
  val fsi: Int => String = i => i.toString

  val result = value.fold(f, fsi)

  println(result)

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a + 1)
  }
}
