package com.kbudunov.lesson.core.functions

//Функции
object Functions extends App {

  def addOne(m: Int): Int = m + 1
  val three = addOne(2)
  println(three)


  def threeExample() = 1 + 2
  println(threeExample)


  def timesTwo(i: Int): Int = {
    println("hello world")
    i * 2
  }
}
