package com.kbudunov.scala.core.fp.functions

object ArgumentsMore extends App {

  def capitalizeAll(args: String*): Seq[String] = {
    args.map { arg =>
      arg.capitalize
    }
  }

  capitalizeAll("rarity", "applejack")
}
