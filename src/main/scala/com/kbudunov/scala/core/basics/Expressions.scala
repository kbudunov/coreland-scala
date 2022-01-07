package com.kbudunov.scala.core.basics

object Expressions extends App {
  // Everything in scala is an expression!
  //don't use var, while cause this is imperativ approach from java

  val x = 1 + 2 //expression
  val y = if (x == 2) 5 else 3 // 'if' is an expression (not instraction)

  //Code blocks
  val aCodeBlocks = { //it's an expression
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

}
