package com.kbudunov.lesson.core.method

object SuperClassMethod extends App {

}

trait Human {
  def hello = "the Human trait"
}
trait Mother extends Human {
  override def hello = "Mother"
}
trait Father extends Human {
  override def hello = "Father"
}

class Child extends Human with Mother with Father {
  def printSuper = super.hello
  def printMother = super[Mother].hello //DDD problem with traits, which realisation to choose like C#
  def printFather = super[Father].hello //DDD problem with traits, which realisation to choose like C#
  def printHuman = super[Human].hello
}
