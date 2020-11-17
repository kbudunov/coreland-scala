package com.kbudunov.lesson.core.patternmatching

import scala.annotation.switch

class Matching {

  val times = 1

  times match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  times match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _           => "some other number"
  }

  def bigger(o: Any): Any = {
    o match {
      case i: Int if i < 0      => i - 1
      case i: Int               => i + 1
      case d: Double if d < 0.0 => d - 0.1
      case d: Double            => d + 0.1
      case text: String         => text + "s"
    }
  }

  //With @switch it works much faster couse
  //This annotation provides a warning at compile time if the switch can’t be
  //compiled to a tableswitch or lookupswitch.
  //Compiling your match expression to a tableswitch or lookupswitch is better for per‐
  //formance, because it results in a branch table rather than a decision tree.
  val id = 1
  val x = (id: @switch) match {
    case 1 => "One"
    case 2 => "Two"
    case _ => "Other"
  }
  //Annotation @switch will throw warning (compiler cant create a branch table and it will works with decision tree)
  val is = 1
  val Two = 2 // added
  val r = (is: @switch) match {
    case 1 => "One"
    case Two => "Two" // replaced the '2' The problem is here!!!
    case _ => "Other"
  }
//  Joshua Suereth states that the following condi‐
//  tions must be true for Scala to apply the tableswitch optimization:
//  1. The matched value must be a known integer.
//  2. The matched expression must be “simple.” It can’t contain any type checks, if statements, or extractors.
//  3. The expression must also have its value available at compile time.
//  4. There should be more than two case statements.

  val i = 5
  val evenOrOdd = i match {
    case 1 | 3 | 5 | 7 | 9  => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
  }

  //This approach is commonly used to create short methods or functions.
  def isTrue(a: Any) = a match {
    case 0 | "" => false
    case _ => true
  }


  def echoWhatYouGaveMe(x: Any): String = x match {

    // constant patterns
    case 0 => "zero"
    case true => "true"
    case "hello" => "you said 'hello'"
    case Nil => "an empty List"

    // sequence patterns
    case List(0, _, _) => "a three-element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
    case Vector(1, _*) => "a vector starting with 1, having any number of elements"

    // tuples
    case (a, b) => s"got $a and $b"
    case (a, b, c) => s"got $a, $b, and $c"

    // constructor patterns
        //case Person(first, "Alexander") => s"found an Alexander, first name = $first"
        //case Dog("Suka") => "found a dog named Suka"

    // typed patterns
    case s: String => s"you gave me this string: $s"
    case i: Int => s"thanks for the int: $i"
    case f: Float => s"thanks for the float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
        //case d: Dog => s"dog: ${d.name}"
    case list: List[_] => s"thanks for the List: $list"
    case m: Map[_, _] => m.toString

    // the default wildcard pattern
    case _ => "Unknown"
  }
}
