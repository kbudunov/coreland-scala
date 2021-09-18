package com.kbudunov.lesson.core.patternmatching

object InsteadOfIsInstanceOf extends App {
  sealed trait SentientBeing
  sealed trait Animal extends SentientBeing
  case class Dog(name: String) extends Animal
  case class Person(name: String, age: Int) extends SentientBeing
  // later in the code ...
  def printInfo(x: SentientBeing): Unit = x match {
    case Person(name, age) => // handle the Person
    case Dog(name)         => // handle the Dog
  }

}
