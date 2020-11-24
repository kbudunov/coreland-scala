package com.kbudunov.lesson.core.method

object AccessMode extends App {

//  in Scala
//  Object-private scope -- method is available only to the current instance of the current object
//  Private
//  Package
//  Package-specific
//  Public
}


class FooObjectPrivate {
//  Object-private scope
  private[this] def isFoo = true
  def doFoo(other: FooObjectPrivate): Unit = {
//    if (other.isFoo) {} // this line won't compile
  }
}

class FooPrivate {
//  Private
  private def isFoo = true
  def doFoo(other: FooPrivate): Unit = {
    if (other.isFoo) { // this now compiles
      // ...
    }
  }
}

class Animal {
  //Package
  protected def breathe: Unit = {}
}
class Dog extends Animal {
  breathe
}

//  Package-specific
package com.acme.coolapp.model {
  class Foo {
    private[model] def doX: Unit = {} // accessable in Package
    private def doY: Unit = {}
  }
  class Bar {
    val f = new Foo
    f.doX // compiles
//    f.doY // won't compile
  }
}
