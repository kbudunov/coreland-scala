package com.kbudunov.lesson.core.fp.monad.either

object EitherForYield extends App {
  //for / yield
  //Пример 1. схлопнул вложенность
  val eitherLoop: Either[String, Int] = for {
    a <- foo(4)
    c <- baz(a + 10)
  } yield c

  println(eitherLoop)

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a + 1)
  }

  def baz(c: Int): Either[String, Int] = {
    if (c < 0) Left(s"$c < 0")
    else Right(c + 1)
  }
}
