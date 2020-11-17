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
}

case class CaseCalculator(brand: String, model: String)