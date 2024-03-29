package com.kbudunov.scala.core.fp.monad.either

object EitherForYield extends App {

  //for / yield
  val eitherLoop: Either[String, Int] = for {
    a <- foo(4)
    c <- { println(s"a = " + a); baz(a + 10) } //if right we are gonna be here
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
