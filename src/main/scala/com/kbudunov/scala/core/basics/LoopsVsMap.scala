package com.kbudunov.scala.core.basics

object LoopsVsMap extends App {
  val upperMap = "hello, world".map(_.toUpper)
  val upperForC = for (c <- "hello, world") yield c.toUpper //analog for map

  // for/yield approaches are used to transform one collection into another
  val result = for {
    c <- "hello, world"
    if c != 'l'
  } yield c.toUpper

  val nieces = List("emily", "hannah", "mercedes", "porsche")
  for (n <- nieces) yield n.capitalize //List(Emily, Hannah, Mercedes, Porsche)

  for (i <- 1 to 10 if i < 4) println(i)

  val names = Map("fname" -> "Robert", "lname" -> "Goren")
  for ((k, v) <- names) println(s"key: $k, value: $v")

  // #1 - source code
  for (i <- 1 to 10) println(i)
  // #1 - compiled code
  1.to(10).foreach(((i) => println(i)))

  // #2 - source code
  for {
    i <- 1 to 10
    if i % 2 == 0
  } println(i)
  // #2 - compiled code
  1.to(10)
    .withFilter(((i) => i.$percent(2).$eq$eq(0)))
    .foreach(((i) => println(i)))

  // #3 - input code
  for {
    i <- 1 to 10
    if i != 1
    if i % 2 == 0
  } println(i)
  // #3 - compiled code
  1.to(10)
    .withFilter(((i) => i.$bang$eq(1)))
    .withFilter(((i) => i.$percent(2).$eq$eq(0)))
    .foreach(((i) => println(i)))

  // #4 - input code
  for { i <- 1 to 10 } yield i
  // #4 - output
  1.to(10).map(((i) => i))

  // #5 - input code (for loop, guard, and yield)
  for {
    i <- 1 to 10
    if i % 2 == 0
  } yield i
  // #5 - translated code
  1.to(10).withFilter(((i) => i.$percent(2).$eq$eq(0))).map(((i) => i))

//  rules:
//  1. A simple for loop that iterates over a collection is translated to a foreach methods call on the collection.
//  2. A for loop with a guard is translated to a sequence of a withFilter
//  methods call on the collection followed by a foreach call.
//  3. A for loop with a yield expression is translated to a map methods call on the collection.
//  4. A for loop with a yield expression and a guard is translated to a withFilter
//  methods call on the collection, followed by a map methods call.
}
