package com.kbudunov.lesson.core.method

object MethodChaining extends App {
  val employee = new Employee
  // use the fluent methods
  employee
    .setFirstName("Al")
    .setLastName("Alexander")
    .setRole("Developer")
  println(employee)
}

class Person {
  protected var fname = ""
  protected var lname = ""

  def setFirstName(firstName: String): this.type = {
    fname = firstName
    this
  }

  def setLastName(lastName: String): this.type = {
    lname = lastName
    this
  }
}

class Employee extends Person {
  protected var role = ""
  def setRole(role: String): this.type = {
    this.role = role
    this
  }
  override def toString = {
    "%s, %s, %s".format(fname, lname, role)
  }
}
