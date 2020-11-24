package com.kbudunov.lesson.core.classes

object CaseClasses extends App {

  val hp20b = CaseCalculator("HP", "20B")
  val hp30b = CaseCalculator("HP", "30B")

  def calcType(calc: CaseCalculator): String = calc match {
    case CaseCalculator("HP", "20B") => "financial"
    case CaseCalculator("HP", "48G") => "scientific"
    case CaseCalculator("HP", "30B") => "business"
    case CaseCalculator(ourBrand, ourModel) => "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
  }

  def calcTypeSimple(calc: CaseCalculator) = calc match {
    case CaseCalculator(_, _) => "Calculator of unknown type"
    case _ => "Calculator of unknown type"
  }

  val a = Person() // corresponds to apply()
  val b = Person("Pam") // corresponds to apply(name: String)
  val c = Person("William Shatner", 82)
  println(a)
  println(b)
  println(c)

}

case class CaseCalculator(brand: String, model: String)

case class Person (var name: String, var age: Int)
object Person {
  def apply() = new Person("<no name>", 0)
  def apply(name: String) = new Person(name, 0)
}
