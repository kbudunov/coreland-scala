package com.kbudunov.lesson.core.bymyself

object FoldImplementation extends App {
  val numbers = List(99, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val List(x, y, z, _*) = numbers
  println(x)

  //unapply example
  val u = User("Ivan", 30)
  val User(name: String, age: Int) = u

  println(name)

}

case class User(name: String, age: Int)

trait Iterable[A] {
  def foldLeft[B](z: B)(op: (B, A) => B): B
}
