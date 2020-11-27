package com.kbudunov.lesson.core.fp.function

object PartiallyAppliedFunction extends App {
  def wrap(prefix: String, html: String, suffix: String): String = {
    prefix + html + suffix
  }

  val wrapWithDiv = wrap("<div>", _: String, "</div>") //partial apply example

  wrapWithDiv("<p>Hello, world</p>")
  wrapWithDiv("<img src=\"/images/foo.png\" />")

}
