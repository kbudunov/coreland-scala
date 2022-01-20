package com.kbudunov.scala.core.collections

import scala.util.Random

object Sequences extends App {

  val aSeq = Seq(9,2,3,4)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(7,6,5))
  println(aSeq.sorted)

//  range
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  //list
  val aList = List(1,2,3,4)
  val prepended = 42 :: aList
  val prepended1 = 42 +: aList
  val appended =   aList :+ 42

  println(aList.mkString("-"))

//  array
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  numbers(2) = 0
  println(numbers)
  println(numbers.mkString(" "))


  //array and seq
//  val numbersSeq: Seq[Int] = numbers //implicit convertions
//  println(numbersSeq)

  //vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vectors vs list
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      //operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))

  // depth in the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
