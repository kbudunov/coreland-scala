package com.kbudunov.scala.core.basics

object MatchCaseClass extends App {

  println(determineType(Dog("Rocky")))
  println(determineType(Cat("Rusty the Cat")))
  println(determineType(Woodpecker))

  def determineType(x: Animal): String = x match {
    case Dog(moniker) => "Got a Dog, name = " + moniker //moniker(not name) name is local variable (unapply)
    case _: Cat       => "Got a Cat (ignoring the name)" //_:Cat cat is instance of Cat
    case Woodpecker   => "That was a Woodpecker" //Woodpecker is object
    case _            => "That was something else"
  }
}

trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal
case object Woodpecker extends Animal
