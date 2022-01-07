package com.kbudunov.scala.core.collections

object MapFlatmapFlatten extends App {

  val numbers = List(1, 2, 3, 4)
  val set = Set(1, 1, 2)

  val hostPort = ("localhost", 80)
  val cartegeExample: (Int, Int) = 1 -> 2
  Map(1 -> 2)

  Some(2)
  None

  numbers.map(_ * 2) //for pure functions
  def timesTwo(i: Int): Int = i * 2
  numbers.map(timesTwo)
  numbers.foreach(_ * 2) //side effects
  numbers.filter(_ % 2 == 0)

  def isEven(i: Int): Boolean = i % 2 == 0
  numbers.filter(isEven)

  List(1, 2, 3, 4).zip(List("a", "b", "c")) //List((1,a), (2,b), (3,c))

  List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).partition(_ % 2 == 0) //(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))
  numbers.find(_ > 5) //Some(6) find the first appropriate element
  numbers.drop(5) //drop first n-members

  numbers.foldLeft(0)((accumulator: Int, n: Int) => accumulator + n) //0 - acc initial value
  numbers.foldRight(0) { (accumulator: Int, n: Int) =>
    accumulator + n
  }
  numbers.sum //much better

  List(List(1, 2), List(3, 4)).flatten //List(1, 2, 3, 4)

  val nestedNumbers = List(List(1, 2), List(3, 4))
  nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten //List(2, 4, 6, 8)
  nestedNumbers.flatMap(x => x.map(_ * 2)) //equivalent of previous one

  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
  extensions.filter { case (_, extension) => extension < 200 }

}
