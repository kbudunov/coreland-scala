package com.kbudunov.scala.core.exceptions

import scala.util.{Failure, Success, Try}

object Tries extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("message lalala"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you")
  def backupMethod(): String = "result"

  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  val potentialFailure2 = Try{
    //......
    unsafeMethod
  }

  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("a valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)


  // map, filter, flatMap
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 2)))
  println(aSuccess.filter(_ > 10))
}
