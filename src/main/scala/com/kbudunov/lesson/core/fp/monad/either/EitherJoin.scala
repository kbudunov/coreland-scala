package com.kbudunov.lesson.core.fp.monad.either

object EitherJoin extends App {
  val r = Seq(1, 3, 4, 5, 6, 8).fold(0) { (z, i) =>
    z + i
  }

  println(r)

  val h: Either[String, Int] = Right(10)
  val h1: Either[String, Either[String, Int]] = Right(h)
  val h2: Either[String, Either[String, Either[String, Int]]] = Right(h1)
  val j1: Either[Either[Int, String], String] = Left(Left(1))

  val t: Either[String, Int] = h2.joinRight.joinRight
  val t2: Either[Int, String] = j1.joinLeft
}
