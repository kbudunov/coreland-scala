package com.kbudunov.scala.core.fp.functions

object Closure extends App {
  //closure allows a functions to access variables outside its immediate lexical scope

  var hello = "Hello"
  def sayHello(name: String): Unit = { println(s"$hello, $name") }
  val foo = new Foo

  // execute sayHello from the exec methods foo
  foo.exec(sayHello, "Al")

  hello = "Hola"
  foo.exec(sayHello, "Lorenzo")

  var votingAge = 18
  val isOfVotingAge = (age: Int) => age >= votingAge
  def printResult(f: Int => Boolean, x: Int): Unit = {
    println(f(x))
  }
  printResult(isOfVotingAge, 20)

}

class Foo {
  // a methods that takes a functions and a string, and passes the string into
  // the functions, and then executes the functions
  def exec(f: (String) => Unit, name: String): Unit = {
    f(name)
  }
}
