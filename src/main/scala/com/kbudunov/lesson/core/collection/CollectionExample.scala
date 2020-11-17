package com.kbudunov.lesson.core.collection

object CollectionExample extends App {
  val numbers = List(1, 2, 3, 4)
  val set = Set(1, 1, 2)

  val hostPort = ("localhost", 80)
  val cartegeExample: (Int, Int) = 1 -> 2
  Map(1 -> 2)

  //trait Option[T] { def isDefined: Boolean def get: T def getOrElse(t: T): T }
  Some(2)
  None

  numbers.map((i: Int) => i * 2) //for pure functions
  def timesTwo(i: Int): Int = i * 2
  numbers.map(timesTwo)

  numbers.foreach((i: Int) => i * 2)  //side effects

  numbers.filter((i: Int) => i % 2 == 0)
  def isEven(i: Int): Boolean = i % 2 == 0
  numbers.filter(isEven)

  List(1, 2, 3).zip(List("a", "b", "c")) //List((1,a), (2,b), (3,c))

  List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).partition(_ % 2 == 0) //(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))

  numbers.find((i: Int) => i > 5) //Some(6) find возвращает первый элемент тот что подходит

  numbers.drop(5) //drop удаляет первые i элементов

  numbers.foldLeft(0)((m: Int, n: Int) => m + n) //0 - перв.знач.аккум. оно записывается в m: Int идет слеваНаправо
  numbers.foldRight(0){(m: Int, n: Int) => m + n }


  List(List(1, 2), List(3, 4)).flatten //List(1, 2, 3, 4)

  val nestedNumbers = List(List(1, 2), List(3, 4))
  nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten //List(2, 4, 6, 8)
  nestedNumbers.flatMap(x => x.map(_ * 2)) //List(2, 4, 6, 8)

  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
  extensions.filter({case (_, extension) => extension < 200})

}
