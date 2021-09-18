package com.kbudunov.lesson.core.base

object ZipWithIndex extends App {
  val days = Array(
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday"
  )

  //zipWithIndex returns a series of Tuple2 elements in an Array
  days.zipWithIndex.foreach {
    case (day, count) => println(s"$count is $day")
  }

  for ((day, count) <- days.zipWithIndex) {
    println(s"$count is $day")
  }

}
