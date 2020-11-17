package com.kbudunov.lesson.core.base

object ForComprehension extends App {
  val names = Array("chris", "ed", "maurice")
  val capNames = for (e <- names) yield e.capitalize
  val lengths = for (e <- names) yield {
    // imagine that this required multiple lines of code
    e.length
  } //Array(5, 2, 7)

}
