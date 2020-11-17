package com.kbudunov.lesson.core.classes


class CalculatorDefault {
  val brand: String = "HP"
  def add(m: Int, n: Int): Int = m + n
}

class Calculator(brand: String) {
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  def add(m: Int, n: Int): Int = m + n
}

class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double): Double = math.log(m) / math.log(base)
}

class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}

abstract class GenericsExample {
  def remove(key: Int): Int
}

trait Car {
  val brand: String
  val number: Int = 10
  final val name: String = "John"

  def log(m: Int): Double
  final def logger(m: Int): Double = m * 10.0
}

class BMW extends Car {
  val brand = "BMW"
  override val number = 11
  println(name)

  override def log(m: Int): Double = m * 5.0
  logger(1)
}