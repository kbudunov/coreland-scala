package com.kbudunov.lesson.core.fp.function

object PartialFunctionCase extends App {

  val pf: PartialFunction[Char, Int] = { //you have to write : PartialFunction[Char, Int] it is nessesary
    case 'a' => 5
    case 'b' => 2
  }

  println("adtb".collect(pf))
}
