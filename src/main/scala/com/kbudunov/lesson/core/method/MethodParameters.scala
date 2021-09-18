package com.kbudunov.lesson.core.method

object MethodParameters extends App {
  val p = new Pizza
  p.update(crustSize = 16, crustType = "Thick") //it works!

  val fruits = List("apple", "banana", "cherry")
  p.printAll(fruits: _*) //printAll method invocation, _* let's transform list to the varargs field

  p.printAll("foo", "bar")

}

class Pizza {
  var crustSize = 12
  var crustType = "Thin"

  def update(crustSize: Int, crustType: String): Unit = {
    this.crustSize = crustSize
    this.crustType = crustType
  }

  override def toString: String = {
    "A %d inch %s crust pizza.".format(crustSize, crustType)
  }

  def printAll(strings: String*): Unit = {
    strings.foreach(println)
  }

}
