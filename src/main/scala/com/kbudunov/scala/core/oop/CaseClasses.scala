package com.kbudunov.scala.core.oop

object CaseClasses extends App {

  val jim = Person("Jim", 34)
  val jim2 = Person("Jim", 34)
  val jim3 = jim.copy(age = 56) //1 - copy

  println(jim.name)
  println(jim.toString) // 2 - toString
  println(jim.hashCode()) // 3 - hashCode
  println(jim.equals(jim2)) //4 - equals
  println(jim3)

  val person = Person //companion object
  val mary = Person("Mary", 32) //companion object invoke

  // 5 CC is serializable by default

  val a = Person() // corresponds to apply()
  val b = Person("Pam") // corresponds to apply(name: String)
  val c = Person("William Shatner", 82)

  // 6 CCs can be used in pattern matching
  def calcType(calc: CaseCalculator): String = calc match {
    case CaseCalculator("HP", "20B") => "financial"
    case CaseCalculator("HP", "48G") => "scientific"
    case CaseCalculator("HP", "30B") => "business"
    case CaseCalculator(ourBrand, ourModel) =>
      "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
  }

  def calcTypeSimple(calc: CaseCalculator): String = calc match {
    case CaseCalculator(_, _) => "Calculator of known type"
    case _                    => "Calculator of unknown type"
  }

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}

case class Person(name: String, age: Int)
case class CaseCalculator(brand: String, model: String)

object Person {
  def apply() = new Person("<no name>", 0)
  def apply(name: String) = new Person(name, 0)
}
