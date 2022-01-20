package com.kbudunov.scala.core.fp.functions

object HOFunctions extends App {

  def executeFunction(callback: () => Unit): Unit = {
    callback()
  }
  val sayHello = () => { println("Hello") }
  executeFunction(sayHello)

  def exec(callback: Int => Unit): Unit = {
    // invoke the functions we were given, giving it an Int parameter
    callback(1)
  }
  val plusOne = (i: Int) => { println(i + 1) }
  exec(plusOne)

  def executeXTimes(callback: () => Unit, numTimes: Int): Unit = {
    for (_ <- 1 to numTimes) callback()
  }

  val sayHello1 = () => println("Hello")
  executeXTimes(sayHello1, 3)

  def executeAndPrint(f: (Int, Int) => Int, x: Int, y: Int): Unit = {
    val result = f(x, y)
    println(result)
  }

  val sum = (x: Int, y: Int) => x + y
  val multiply = (x: Int, y: Int) => x * y
  executeAndPrint(sum, 2, 9) // prints 11
  executeAndPrint(multiply, 3, 9) // prints 27

// implicit approach
  // 1 - define the methods
  def exec(callback: (Any, Any) => Unit, x: Any, y: Any): Unit = {
    callback(x, y)
  }
  // 2 - define a functions to pass in
  val printTwoThings = (a: Any, b: Any) => { //implicit approach here
    println(a)
    println(b)
  }
  // 3 - pass the functions and some parameters to the methods
  case class Person(name: String)
  exec(printTwoThings, "Hello", Person("Dave"))

//explicit approach
  // 2a - define a methods to pass in
  def printTwoThings(a: Any, b: Any): Unit = { //explicit approach here
    println(a)
    println(b)
  }
  // 3a - pass the printTwoThings methods to the exec methods
  exec(printTwoThings, "Hello", Person("Dave"))

  //  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusTwo = (x: Int) => x + 2
  println(nTimes(plusTwo, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x)) //curring

  val plus10 = nTimesBetter(plusTwo, 10)
  println(plus10(1))

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) //y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))


  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") //you have to write return type
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
}
