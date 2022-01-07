package com.kbudunov.scala.core.fp.monad.either

object EitherJoin extends App {

  val h: Either[String, Int] = Right(10)
  val h1: Either[String, Either[String, Int]] = Right(h)
  val h2: Either[String, Either[String, Either[String, Int]]] = Right(h1)
  val j1: Either[Either[Int, String], String] = Left(Left(1))

  val t: Either[String, Int] = h2.joinRight.joinRight
  val t2: Either[Int, String] = j1.joinLeft
}
