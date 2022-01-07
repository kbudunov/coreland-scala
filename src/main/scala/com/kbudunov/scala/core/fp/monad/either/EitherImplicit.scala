package com.kbudunov.scala.core.fp.monad.either

object EitherImplicit extends App {

  type Error = String

  val either_1: Either[Error, Int] = Right(5)
  val either_2: Int => Either[Error, Int] = (a: Int) => either_1.map(_ + a)
  val either_3: Either[Error, Int] = Left("")

  private val value1: Either[Error, Int] = either_1.andThen(either_2)

  implicit class EitherOps[T](value: Either[Error, T]) {
    def andThen[A](action: T => Either[Error, A]): Either[Error, A] = {
      value.flatMap(action)
    }
  }
}
