package com.kbudunov.lesson.core.patternmatching

//Сопоставление с образцом
class Matching {

  val times = 1

  times match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  //Сопоставление с использованием условий
  times match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _ => "some other number"
  }
}
