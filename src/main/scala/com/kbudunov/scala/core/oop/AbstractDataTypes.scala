package com.kbudunov.scala.core.oop

object AbstractDataTypes extends App {

  //abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")
  }

  //traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat" //it can have fields
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit =
      println(s"Im a croc and im eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //traits vs abstract oop
  //1 - traits don't have constructor parameters
  //2 - multiple traits maybe inherited by the same class
  //3 - traits == behavior, abstract class == data

  //Scala oop hierarchy
  //                                    scala.Any
  //    scala.AnyVal (java.lang.Object)           scala.AnyRef (java.lang.Object)
  //      Int, Unit, Boolean, Float ..                String, List, Set ....
  //                                                      scala.Null
  //                                 scala.Nothing
}
