package com.kbudunov.lesson.core.base

object ForYieldInsteadMap extends App {
  case class Person (name: String)
  val friends = Vector("Mark", "Regina", "Matt")

  val result = for (f <- friends) yield Person(f) //it works like map

  //Unlike the for/yield approach shown in the previous recipe, the map method also works
  //well when writing a chain of method calls.
  val helpers = Vector("adam", "kim", "melissa")
  val caps = helpers.map(e => e.capitalize)

}
