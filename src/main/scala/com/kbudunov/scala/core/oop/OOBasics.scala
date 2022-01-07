package com.kbudunov.scala.core.oop

object OOBasics extends App {

  val person = new Person("Ivan", 28)
  println(person.x)
  person.greet("Daniel")

  //val age: Int --gives us getter
  class Person(name: String, val age: Int) {
    val x = 2
    def greet(name: String): Unit = println(s"$name hi! ${this.name}")
    def greet(): Unit = println(s"Hi I am $name")

    //multiple constructors
    def this(name: String) = this(name, 0)
    def this() = this("John")
  }

}
