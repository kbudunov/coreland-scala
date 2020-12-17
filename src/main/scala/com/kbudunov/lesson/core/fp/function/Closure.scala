package com.kbudunov.lesson.core.fp.function

//closure allows a function to access variables outside its immediate lexical scope
object Closure extends App {
  var hello = "Hello" //Achtung es ist VAR!!!
  def sayHello(name: String): Unit = { println(s"$hello, $name") }
  val foo = new Foo

  // execute sayHello from the exec method foo
  foo.exec(sayHello, "Al")

  // change the local variable 'hello', then execute sayHello from
  hello = "Hola"
  // the exec method of foo, and see what happens
  foo.exec(sayHello, "Lorenzo")

  var votingAge = 18
  val isOfVotingAge = (age: Int) => age >= votingAge
  def printResult(f: Int => Boolean, x: Int): Unit = {
    println(f(x))
  }
  printResult(isOfVotingAge, 20)

}

class Foo {
  // a method that takes a function and a string, and passes the string into
  // the function, and then executes the function
  def exec(f:(String) => Unit, name: String): Unit = {
    f(name)
  }
}
