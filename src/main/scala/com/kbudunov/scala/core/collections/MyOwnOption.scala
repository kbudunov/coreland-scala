package com.kbudunov.scala.core.collections

abstract class MyOwnOption[+T] {
  def map[B](f: T => B): MyOwnOption[B]
  def flatMap[B](f: T => MyOwnOption[B]): MyOwnOption[B]
  def filter(p: T => Boolean): MyOwnOption[T]
}

case object MaybeNot extends MyOwnOption[Nothing] {
  override def map[B](f: Nothing => B): MyOwnOption[B] = MaybeNot

  override def flatMap[B](f: Nothing => MyOwnOption[B]): MyOwnOption[B] = MaybeNot

  override def filter(p: Nothing => Boolean): MyOwnOption[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends MyOwnOption[T] {
  override def map[B](f: T => B): MyOwnOption[B] = Just(f(value))

  override def flatMap[B](f: T => MyOwnOption[B]): MyOwnOption[B] = f(value)

  override def filter(p: T => Boolean): MyOwnOption[T] =
    if(p(value)) this
    else MaybeNot
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}