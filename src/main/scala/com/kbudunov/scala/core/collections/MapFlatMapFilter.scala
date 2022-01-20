package com.kbudunov.scala.core.collections

object MapFlatMapFilter extends App {

  val list = List(1,2,3)
  println(list)

  //map
  println(list.map(_ + 1))
//  println(list.map(_ + " is a number"))

  //flatMap
//  val toPair = (x: Int) => List(x, x+1)
//  println(list.flatMap(toPair))

  //print all combinators between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("white", "red")
  //List("a1", "a2", ... "d4")

  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + color)))
  println(combinations)

  //foreach
  list.foreach(println)

  // for-comprehensions
  val farCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + color


}
