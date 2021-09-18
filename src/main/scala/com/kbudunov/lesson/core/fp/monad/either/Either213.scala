package com.kbudunov.lesson.core.fp.monad.either

object Either213 extends App {

  //since 2.13 Right is default
  val l: Either[String, Int] = foo(3).map(i => i * 2)

  //println(t.right) right is depricated
  val o: Option[Int] = l.toOption //you don't need to write  l.right.toOption since 2.13

  def foo(a: Int): Either[String, Int] = {
    if (a < 0) Left(s"$a < 0")
    else Right(a + 1)
  }
}
