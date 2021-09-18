package com.kbudunov.lesson.core.patternmatching

object MatchCaseClass extends App {
  println(determineType(Dog("Rocky")))
  println(determineType(Cat("Rusty the Cat")))
  println(determineType(Woodpecker))

  def determineType(x: Animal): String = x match {
    case Dog(moniker) =>
      "Got a Dog, name = " + moniker //moniker(не name) имя как локальная переменная (берется в unapply по порядку)
    case _: Cat     => "Got a Cat (ignoring the name)" //_:Cat потому что экземпляр
    case Woodpecker => "That was a Woodpecker" //потому что object
    case _          => "That was something else"
  }
}

trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal
case object Woodpecker extends Animal
