package com.kbudunov.lesson.core.fp.function

object ReturnFunction extends App {
  def saySomething(prefix: String): String => String = (s: String) => {
    prefix + " " + s
  }
  val sayHello: String => String = saySomething("Hello")
  sayHello("Al")

  def greeting(language: String): String => String = (name: String) => {
    language match {
      case "english" => "Hello, " + name
      case "spanish" => "Buenos dias, " + name
    }
  }
  val hello: String => String = greeting("english")
  val buenosDias: String => String = greeting("spanish")
  hello("Al")
  buenosDias("Lorenzo")

}
