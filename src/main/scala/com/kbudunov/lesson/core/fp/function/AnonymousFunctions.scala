package com.kbudunov.lesson.core.fp.function

object AnonymousFunctions extends App {
  val x = List.range(1, 10)

  //function literal(anonymous function)
  val fl: Int => Boolean = (i: Int) => i % 2 == 0

  val evens = x.filter(fl)
  println(evens)

  //simplification example
  val evens1 = x.filter((i: Int) => i % 2 == 0)
  val evens2 = x.filter(i => i % 2 == 0)
  val evens3 = x.filter(_ % 2 == 0)
}
