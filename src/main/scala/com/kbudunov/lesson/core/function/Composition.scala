package com.kbudunov.lesson.core.function

object Composition extends App {
  def addUmm(x: String): String = x + " umm" // g()
  def addAhem(x: String): String = x + " ahem" //f()

  val ummThenAhem: String => String = addAhem _ compose addUmm _ //f(g(x)) результат протягивается слеваНаправо
  val r = ummThenAhem("well") //well umm ahem

  val ahemThenUmm: String => String = addAhem _ andThen addUmm _ //g(f(x) результат протягивается справаНалево
  val l = ahemThenUmm("well") //well ahem umm

  println(r)
  println(l)
}
