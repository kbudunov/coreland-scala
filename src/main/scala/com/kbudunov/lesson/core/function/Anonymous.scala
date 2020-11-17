package com.kbudunov.lesson.core.function

object Anonymous extends App {

  val sayHello = () => println("hello")
  val addOne = (x: Int) => x + 1
  val sum = (x: Int, y: Int) => x + y

  val multiply = { i: Int =>
    println("hello world")
    i * 2
  }

  println(multiply(4))
}
