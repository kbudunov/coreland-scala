package com.kbudunov.lesson.core.fp.monad.either

object EitherFlatMap extends App {
  //Problem
  val r: Either[String, Either[String, Int]] =
    foo(3).map(rightValue => baz(rightValue))
  //Solving approach 1. joinRight
  val r1: Either[String, Int] = r.joinRight

  //Solving approach 2. .flatMap()
  val r2: Either[String, Int] = foo(3).flatMap(rightValue => baz(rightValue))

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a + 1)
  }

  def baz(c: Int): Either[String, Int] = {
    if (c < 0) Left(s"$c < 0")
    else Right(c + 1)
  }
}

trait Tesststs {
  val f = 150
  var r = 200

}