package com.kbudunov.lesson.core.fp.function

object Currying extends App {
  //Можно каррировать любую уже существующую функцию
  val sum: (Int, Int) => Int = _ + _
  val sumCurried: Int => Int => Int = sum.curried
  println(sumCurried)

  def sizeConstraint(pred: String, n: Int, email: String): String =
    pred + n + email
  val sizeConstraintFunction: (String, Int, String) => String = sizeConstraint

  def sizeConstraintCurr(pred: String)(n: Int)(email: String): String =
    pred + n + email
  val sizeConstraintFunctionCurr: String => Int => String => String =
    sizeConstraintCurr

  //частично применяем, и протаскиваем параметр в мешке а не как в джаве
  val r: Int => String => String = sizeConstraintFunctionCurr("s")
  val r2: String => String = r(1)
  val r3: String = r2("m")
  println(r3)

}
