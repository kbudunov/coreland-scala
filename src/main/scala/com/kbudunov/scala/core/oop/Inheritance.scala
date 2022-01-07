package com.kbudunov.scala.core.oop

object Inheritance extends App {

  //single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnom") //private protected public_byDefault
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch()

  //constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  //type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K8")
  unknownAnimal.eat

  //overRIDING vs overLoading
  //super
  //preventing overrides
  //1 - use final on member
  //2 - use final on class
  //3 - sealed the class = extends in this file only

}
