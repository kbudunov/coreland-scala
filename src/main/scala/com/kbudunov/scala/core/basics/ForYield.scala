package com.kbudunov.scala.core.basics

object ForYield extends App {

  def gameResults(): Seq[(String, Int)] =
    ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

  //map with filter
  def hallOfFame: Seq[String] =
    for {
      result <- gameResults()
      (name, score) = result //unapply
      if score > 5000
    } yield name.capitalize

  val lists = List(1, 2, 3) :: List.empty :: List(5, 3) :: Nil

  for {
    list @ head :: _ <- lists
  } yield list.size

  val names = Map("fname" -> "Ed", "lname" -> "Chigliak")
  for ((k, v) <- names) println(s"key: $k, value: $v")

}
