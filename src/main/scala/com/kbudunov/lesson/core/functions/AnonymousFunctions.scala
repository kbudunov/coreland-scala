package com.kbudunov.lesson.core.functions

//Анонимные функции
object AnonymousFunctions extends App {

  val addOne = (x: Int) => x + 1
  println(addOne(1))

  //Если ваша функция состоит из множества выражений, вы можете использовать фигурные скобки {}
  val multiply = { i: Int =>
    println("hello world")
    i * 2
  } //Вы часто будете видеть подобный синтаксис при передачи анонимной функции в качестве параметра.
  println(multiply(4))

}
