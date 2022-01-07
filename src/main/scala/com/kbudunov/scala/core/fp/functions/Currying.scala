package com.kbudunov.scala.core.fp.functions

object Currying extends App {
  //You can caring any function
  val sum: (Int, Int) => Int = _ + _
  val sumCurried: Int => Int => Int = sum.curried //curried
  println(sumCurried)

  def sizeConstraint(pred: String, n: Int, email: String): String =
    pred + n + email
  val sizeConstraintFunction: (String, Int, String) => String = sizeConstraint

  def sizeConstraintCurr(pred: String)(n: Int)(email: String): String =
    pred + n + email
  val sizeConstraintFunctionCurr: String => Int => String => String =
    sizeConstraintCurr

  //partial applying
  val r: Int => String => String = sizeConstraintFunctionCurr("s") //step 1
  val r2: String => String = r(1) //step 2
  val r3: String = r2("m") // step 3
  println(r3)

}
