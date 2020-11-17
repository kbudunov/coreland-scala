package com.kbudunov.lesson.core.function

object ArgumentsMore extends App {

  def capitalizeAll(args: String*): Seq[String] = {
    args.map { arg =>
      arg.capitalize
    }
  }

  capitalizeAll("rarity", "applejack")
}
