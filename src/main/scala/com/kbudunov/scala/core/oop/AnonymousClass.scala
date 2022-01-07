package com.kbudunov.scala.core.oop

object AnonymousClass extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahha")
  }

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hell im $name")
  }

  val jim = new Person("John") { // you can create instance of AnonymousClass, Trait, AbstractClass, Regular Class
    override def sayHi: Unit = println("Haha")
  }

}
