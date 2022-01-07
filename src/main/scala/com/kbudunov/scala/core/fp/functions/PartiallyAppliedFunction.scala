package com.kbudunov.scala.core.fp.functions

object PartiallyAppliedFunction extends App {

  def wrap(prefix: String, html: String, suffix: String): String = {
    prefix + html + suffix
  }

  val wrapWithDiv = wrap("<div>", _: String, "</div>") //partial apply example

  wrapWithDiv("<p>Hello, world</p>")
  wrapWithDiv("<img src=\"/images/foo.png\" />")

  def adder(m: Int, n: Int): Int = m + n
  val add2 = adder(2, _)
  println(add2(3))

}
