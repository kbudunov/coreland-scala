package com.kbudunov.scala.core.fp.functions

object CurryingImplementation extends App {

  val superAdder: Function1[Int, Function[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  //short version
  val superAdd = (x: Int) => (y: Int) => x + y

  println(superAdd(7)(7))

  val adder = superAdder(3)
  println(adder(4))

  println(superAdder(5)(5))


  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def compose2[A,B,C](f: A => B, g: C => A): C => B =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def andThen2[A,B,C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)

  def add4: Int => Int = superAdder2(4)
  println(add4(17))

  def simpleAdder: (Int, Int) => Int = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
