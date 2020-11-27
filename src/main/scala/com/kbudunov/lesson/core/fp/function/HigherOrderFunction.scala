package com.kbudunov.lesson.core.fp.function

object HigherOrderFunction extends App {
  def executeFunction(callback:() => Unit): Unit = {
    callback()
  }
  val sayHello = () => { println("Hello") }
  executeFunction(sayHello)


  def exec(callback: Int => Unit): Unit = {
    // invoke the function we were given, giving it an Int parameter
    callback(1)
  }
  val plusOne = (i: Int) => { println(i+1) }
  exec(plusOne)


  def executeXTimes(callback:() => Unit, numTimes: Int): Unit = {
    for (i <- 1 to numTimes) callback()
  }
  val sayHello1 = () => println("Hello")
  executeXTimes(sayHello1, 3)


  def executeAndPrint(f:(Int, Int) => Int, x: Int, y: Int): Unit = {
    val result = f(x, y)
    println(result)
  }
  val sum = (x: Int, y: Int) => x + y
  val multiply = (x: Int, y: Int) => x * y
  executeAndPrint(sum, 2, 9) // prints 11
  executeAndPrint(multiply, 3, 9) // prints 27

// implicit approach
  // 1 - define the method
  def exec(callback: (Any, Any) => Unit, x: Any, y: Any): Unit = {
    callback(x, y)
  }
  // 2 - define a function to pass in
  val printTwoThings = (a: Any, b: Any) => { //implicit approach here
    println(a)
    println(b)
  }
  // 3 - pass the function and some parameters to the method
  case class Person(name: String)
  exec(printTwoThings, "Hello", Person("Dave"))



//explicit approach
  // 2a - define a method to pass in
  def printTwoThings (a: Any, b: Any): Unit = { //explicit approach here
    println(a)
    println(b)
  }
  // 3a - pass the printTwoThings method to the exec method
  exec(printTwoThings, "Hello", Person("Dave"))


}
