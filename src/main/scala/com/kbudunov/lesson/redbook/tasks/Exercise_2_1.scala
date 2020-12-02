package com.kbudunov.lesson.redbook.tasks

object Exercise_2_1 extends App {



  println(fibLoops(5))

  def fibLoops(a: Int): Int ={
    var result = 0
    if(a == 0 || a == 1) 1 else {
      for (i <- 2 until a) {
        val r1 = 0
        val r2 = 0
        result = 1 + 1
      }
      result
    }

  }






//  fib(3)

  def fib(n: Int): Int = {

    def go(a: Int, b: Int): Int = {
      a + b
    }

    go(n, 0)
  }

}
