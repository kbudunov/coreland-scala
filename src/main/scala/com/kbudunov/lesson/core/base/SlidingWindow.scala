package com.kbudunov.lesson.core.base

object SlidingWindow extends App {
  val nums = (1 to 5).toArray

  // size = 2 , size of 1
  val s = nums.sliding(2).toList //List(Array(1, 2), Array(2, 3), Array(3, 4), Array(4, 5))

  // size = 2, step = 2
  val s1 = nums.sliding(2,2).toList //List(Array(1, 2), Array(3, 4), Array(5))

  // size = 2, step = 3
  val s2 = nums.sliding(2,3).toList //List(Array(1, 2), Array(4, 5))

}
