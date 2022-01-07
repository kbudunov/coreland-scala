package com.kbudunov.scala.core.oop

object Objects extends App {
  //there are no Static in scala

  //companion object
  object Person { //Object can't take parameters in constructor
    val N_EYES = 2
    def canFly: Boolean = false

    //factory methods
    def apply(father: Person, mother: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {}

  println(Person.N_EYES)
  println(Person.canFly)

  //Scala object == Singleton instance
  val mary = new Person("Mary")
  val john = new Person("John")

  println(mary == john)

  val bobbie = Person(mary, john)

  Timer.currentCount()
}

object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}
