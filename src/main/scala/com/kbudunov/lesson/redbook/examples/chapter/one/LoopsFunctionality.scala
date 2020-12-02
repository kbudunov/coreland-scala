package com.kbudunov.lesson.redbook.examples.chapter.one

object LoopsFunctionality extends App {


  //without mutating a loop variable ()
  def factorial(n: Int): Int = {

    //often called go or loop by convention
    @scala.annotation.tailrec //
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
//      else 1 + go(n-1, n*acc) //doesn't compile because @scala.annotation.tailrec/ The compiler can't find tail recursion
      else go(n-1, n*acc)
    go(n, 1)
  }
}
