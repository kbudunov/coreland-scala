package com.kbudunov.scala.core.basics

object CallByNameValue extends App {

  def calledByValue(x: Long): Unit = { //x will be calculated BEFORE invoke
    println("calledByValue: " + x)
    println("calledByValue: " + x)
  }

  def calledByName(x: => Long): Unit = { //x: => Long call by Name
    println("calledByValue: " + x) //186807251247300 it will be calculated AFTER invoke
    println("calledByValue: " + x) //186807251337500 it will be calculated AFTER invoke
  }

  calledByValue(System.nanoTime()) //186807085955700 it will be calculated BEFORE invoke
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

//  printFirst(infinite(), 34) StackOverflow
  printFirst(34, infinite()) //y never gonna be calculated: => Int

}
