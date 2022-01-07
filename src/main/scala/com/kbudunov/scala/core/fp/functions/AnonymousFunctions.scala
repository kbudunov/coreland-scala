package com.kbudunov.scala.core.fp.functions

object AnonymousFunctions extends App {

  val doubler1: Int => Int = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2
  val doubler3: Int => Int = _ * 2

  val adder1 = (a: Int, b: Int) => a + b
  val adder2: (Int, Int) => Int = (a, b) => a + b
  val adder3: (Int, Int) => Int = _ + _
  val adder4: Int => Int => Int = a => b => a + b

  val noParam: () => Int = () => 3

  val x = List.range(1, 10)

  //functions literal(anonymous functions)
  val fl: Int => Boolean = (i: Int) => i % 2 == 0

  val evens = x.filter(fl)
  println(evens)


  //simplification example
  val evens1 = x.filter((i: Int) => i % 2 == 0)
  val evens2 = x.filter(i => i % 2 == 0)
  val evens3 = x.filter(_ % 2 == 0)

  val sayHello = () => println("hello")
  val addOne = (x: Int) => x + 1
  val sum = (x: Int, y: Int) => x + y

  val multiply = { i: Int =>
    println("hello world")
    i * 2
  }
  println(multiply(4))

  val plusOne = new AddOne()
  val plusTwo = new AddTwo()
  println(plusOne(1))
  println(plusTwo(1))

}

class AddOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}

class AddTwo extends (Int => Int) {
  def apply(m: Int): Int = m + 2
}
