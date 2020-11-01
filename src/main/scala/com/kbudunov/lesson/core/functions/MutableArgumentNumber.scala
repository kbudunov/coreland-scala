package com.kbudunov.lesson.core.functions

//Использование переменного количества аргументов
object MutableArgumentNumber extends App {
  def capitalizeAll(args: String*) = {
    args.map { arg =>
      arg.capitalize
    }
  }

  capitalizeAll("rarity", "applejack")
}
