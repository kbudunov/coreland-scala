package com.kbudunov.lesson.core.types

object MethodWithGeneric extends App {
  val names = Seq("Aleka", "Christina", "Tyler", "Molly")
  val winner = randomElement(Seq("Aleka", "Christina", "Tyler", "Molly"))
  randomElement(List(1,2,3))
  randomElement(List(1.0,2.0,3.0))
  randomElement(Vector.range('a', 'z'))

  println(winner)

  def randomElement[A](seq: Seq[A]): A = {
    val randomNum = util.Random.nextInt(seq.length)
    seq(randomNum)
  }
}
