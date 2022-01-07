package com.kbudunov.scala.core.regexp

object Regexp extends App {

  val numPattern = "[0-9]+".r
  val address = "123 Main Street Suite 101"
  val match1 = numPattern.findFirstIn(address)
  val matches = numPattern.findAllIn(address)

  val newAddress = numPattern.replaceAllIn("123 Main Street", "x")

  println(match1)

}
