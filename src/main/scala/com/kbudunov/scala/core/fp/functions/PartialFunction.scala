package com.kbudunov.scala.core.fp.functions

object PartialFunction extends App {

  // partial functions is a functions that does not provide an answer for every possible input value

  //it is regular functions, but it can't return result when 0 takes as input parameter. It return exceptions.
  val divide = (x: Int) => 42 / x

  //in Scala exceptions == evil
  //Stage 1. Make a group of functions
  val dividePF: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = 42 / x //the override method
    def isDefinedAt(x: Int): Boolean = x != 0 //the override method
  }

  //Stage 2. Apply
  if (dividePF.isDefinedAt(1)) dividePF(1)

  //often written using case statements
  //notice it will work only for subSet of input values
  val divide2: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }

  // converts 1 to "one", etc., up to 5
  val convertLowNumToString: PartialFunction[Int, String] =
    new PartialFunction[Int, String] {
      val nums: Array[String] = Array("one", "two", "three", "four", "five")
      def apply(i: Int): String = nums(i - 1)
      def isDefinedAt(i: Int): Boolean = i > 0 && i < 6
    }

}
