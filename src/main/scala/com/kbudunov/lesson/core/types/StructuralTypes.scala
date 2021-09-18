package com.kbudunov.lesson.core.types

// StructuralTypes or DuckTypes
object StructuralTypes extends App {

//  callSpeak(new Dog)
//  callSpeak(new Klingon)
//  callSpeak(new Kat) won't compile

  // пролезут только те типы у которых реализован метод соответствующей сигнатуры
//  def callSpeak[A <: { def speak(): Unit } ](obj: A): Unit = {
//    obj.speak()
//  }
}

class Dog {
  def speak(): Unit = { println("woof") }
  def makeNoise(message: String): Unit = { println("woof!!!") }
}
class Klingon { def speak(): Unit = { println("Qapla!") } }

class Kat { def makeNoise(message: String): Unit = { println("myaw!!!") } }
