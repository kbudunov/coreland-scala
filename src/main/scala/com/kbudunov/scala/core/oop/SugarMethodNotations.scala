package com.kbudunov.scala.core.oop

object SugarMethodNotations extends App {

  //More Sugar
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"
    def unary_! : String =
      s"$name, what the heck?!" //it's reserved word: unary_   --> println(!mary)
    def isAlive: Boolean = true //не принимает параметры
    def apply(): String = s"Hi my name is $name" //it's reserved word: apply
  }

  val mary = new Person("Mary", "Inception")

  //Sugar 1 - INFIX NOTATION
  println(mary.likes("Inception"))
  println(mary likes "Inception") //infix notation = operator notation

  //All operators == methods in Scala
  //'operators' in Scala (method 'hangOutWith' with one parameter, works such us  + - / --)
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)

  println(1 + 2)
  println(1.+(2))

  //Sugar 2 - PREFIX NOTATION
  val x = -1 //unary_-
  val y = 1.unary_-

  //unary_ only works with - + ~ !
  println(!mary) //unary_!
  println(mary.unary_!)

  //Sugar 3 - POSTFIX NOTATION
  import scala.language.postfixOps

  println(mary.isAlive)
  println(mary isAlive) //postfix notation

  //Sugar 4 - APPLY
  println(mary.apply())
  println(mary()) //invokes apply

}
