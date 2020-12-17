package com.kbudunov.lesson.core.classes

object Beginning extends App {
  class PersonVar(var name: String)
  val pVar = new PersonVar("Alvin Alexander")
  println(pVar.name)
  pVar.name = "Fred Flintstone"

  class PersonVal(val name: String)
  val pVal = new PersonVal("Alvin Alexander")
  println(pVal.name)
//  pVal.name = "Fred Flintstone"

  class Person(name: String)
  val p = new Person("Alvin Alexander")
//  p.name
//  p.name = "Fred Flintstone"


}
