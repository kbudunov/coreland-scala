package com.kbudunov.lesson.core.fp.function

object FunctionsAsVariables extends App {
  val double: Int => Int = (i: Int) => { i * 2 }

  val f1: (Int) => Boolean = i => { i % 2 == 0 }
  val f2: Int => Boolean = i => { i % 2 == 0 }
  val f3: Int => Boolean = i => i % 2 == 0
  val f4: Int => Boolean = _ % 2 == 0

  // implicit approach
  val add1 = (x: Int, y: Int) => { x + y }
  val add2 = (x: Int, y: Int) => x + y

  // explicit approach
  val add3: (Int, Int) => Int = (x,y) => { x + y }
  val add4: (Int, Int) => Int = (x,y) => x + y
  val addThenDouble: (Int, Int) => Int = (x,y) => {
    val a = x + y
    2 * a
  }

  println(double(2))


  def modMethod1(i: Int): Boolean = i % 2 == 0
  def modMethod2(i: Int): Boolean = { i % 2 == 0 }
  def modMethod3(i: Int): Boolean = i % 2 == 0
  def modMethod4(i: Int): Boolean = { i % 2 == 0 }

  val list = List.range(1, 10)
  list.filter(modMethod1)

}
