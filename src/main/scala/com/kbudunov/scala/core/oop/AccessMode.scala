package com.kbudunov.scala.core.oop

object AccessMode extends App {

//  in Scala
//  Object-private scope -- methods is available only to the current instance of the current object
//  Private
//  Package
//  Package-specific
//  Public

  class Foo {
    //private[model] def doX: Unit = {} // accessible in Package
    private def doY: Unit = {}
  }

  class Bar {
    val f = new Foo
    //f.doX // compiles
    //f.doY // won't compile
  }

  class Animal {
    //Package
    protected def breathe: Unit = {}
  }
  class Dog extends Animal {
    breathe
  }
}
