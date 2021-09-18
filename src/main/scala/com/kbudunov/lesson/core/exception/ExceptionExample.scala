package com.kbudunov.lesson.core.exception

class ExceptionExample {
  try {
    //do smth
  } catch {
    case e: IllegalArgumentException => println(e.printStackTrace())
    case e: RuntimeException         => println(e.printStackTrace())
  } finally {}
}
