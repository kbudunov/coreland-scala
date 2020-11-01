package com.kbudunov.lesson.core.classes

class Calculator(brand: String) {
  /**
   * Конструктор.
   */
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // Метод экземпляра класса.
  def add(m: Int, n: Int): Int = m + n
}

class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}