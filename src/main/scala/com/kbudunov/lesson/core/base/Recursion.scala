package com.kbudunov.lesson.core.base

object Recursion extends App {
  val r: Long = ('a'.toInt) * ('b'.toInt) * ('c'.toInt)
  println(r)

  println(calculate("abc"))

  def calculate(s: String): Int = {
    s.headOption match {
      case Some(currentSymbolValue) =>
        currentSymbolValue.toInt * calculate(s.tail)
      case None => 1
    }
  }

  def listToString(list: List[String]): String = list match {
    case s :: rest => s + " " + listToString(rest)
    case Nil       => ""
  }
}
