package com.kbudunov.scala.core.methods

object DDDProblemResolution extends App {}

trait Human {
  def hello = "the Human trait"
}
trait Mother extends Human {
  override def hello = "Mother"
}
trait Father extends Human {
  override def hello = "Father"
}

//DDD problem resolver - super[Mother].hello
class Child extends Human with Mother with Father {
  def printSuper = super.hello

  //DDD problem with traits, which realisation to choose like in C#
  def printMother = super[Mother].hello
  def printFather = super[Father].hello
  def printHuman = super[Human].hello
}
