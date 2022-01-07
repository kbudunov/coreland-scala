package com.kbudunov.scala.core.basics

object StringOps extends App {
  val str = "Hello!!"

  str.charAt(2)
  str.substring(7, 11)
  str.split(" ").toList
  str.startsWith("Hello")
  str.replace(" ", "_")
  str.toLowerCase
  str.toUpperCase
  str.length
  str.reverse
  str.take(2)

  val numberString = "45"
  numberString.toInt

  println('a' +: numberString :+ 'z')

  //S-interpolator
  val name = "David"
  val age = 12
  val greetings = s"Lalala $name, ${age + 2}."

  //F-interpolation
  val speed = 1.2f
  val myth = f"$name lalalal $speed%2.2f"

  //raw-interpolator
  val escape = "This is a \n newLine"
  println(raw"$escape")

}
