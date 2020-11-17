package com.kbudunov.lesson.core.exception

class ExceptionExample {
  try {
    //do smth
  } catch {
    case e: IllegalArgumentException => println("")
    case e: RuntimeException => println("")
  } finally {

  }
}
