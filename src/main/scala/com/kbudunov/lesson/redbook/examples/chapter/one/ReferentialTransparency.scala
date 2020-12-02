package com.kbudunov.lesson.redbook.examples.chapter.one

object ReferentialTransparency extends App {

  //referential transparency (RT) in action
  val x = "Hello, World"
  val rt1 = x.reverse
  val rt2 = x.reverse

  //same results
  val rt1Same = "Hello, World".reverse
  val rt2Same = "Hello, World".reverse


  val e = new StringBuilder("Hello")
  val y = e.append(", World")
  val r1 = y.toString
  val r2 = y.toString

  //It is not RT
  //different results
  val t = new StringBuilder("Hello") //because it's mutable
  val t1 = t.append(", World").toString //this change state of t
  val t2 = t.append(", World").toString //  Hello, World, World
}
