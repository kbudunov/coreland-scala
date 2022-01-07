package com.kbudunov.scala.core.fp.functions

object PartialFunctionChaining extends App {
  //Main feature of partial functions is that, you can chain them together!

  val pf: PartialFunction[Char, Int] = { //you have to write : PartialFunction[Char, Int] it is necessary
    case 'a' => 5
    case 'b' => 2
  }

  println("adtb".collect(pf))

  // converts 1 to "one", etc., up to 5
  val convert1to5 = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i - 1)
    def isDefinedAt(i: Int) =
      i > 0 && i < 6 //it can be implemented not for all real world objects
  }
  // converts 6 to "six", etc., up to 10
  val convert6to10 = new PartialFunction[Int, String] {
    val nums = Array("six", "seven", "eight", "nine", "ten")
    def apply(i: Int) = nums(i - 6)
    def isDefinedAt(i: Int) =
      i > 5 && i < 11 //it can be implemented not for all real world objects
  }

  val handle1to10 = convert1to5 orElse convert6to10 //orElse or andThen

  println(handle1to10(3))
  println(handle1to10(8))

  val divide: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }

  // PartialFunction with MAP
  //List(0,1,2) map { divide } //it does not work! Because map methods does not invoke isDefinedAt methods
  //Notice that have a different type elements!
  //List(42, "cat") map { case i: Int     => i + 1 } //It does not work!

  //PartialFunction with COLLECT
  List(0, 1, 2) collect { divide } //It works because collect methods invokes isDefinedAt under the hood
  //Notice that have a different type elements!
  val result: Seq[Int] = List(42, 11, "cat") collect { case i: Int => i + 1 } //It works!
  println(result)

}
