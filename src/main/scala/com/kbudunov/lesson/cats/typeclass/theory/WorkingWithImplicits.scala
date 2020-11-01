package com.kb.lesson.cats.typeclass.theory

class WorkingWithImplicits {

  //  In object or trait
  //  Packaging implicits in Scala must be placed inside an object or trait rather than at the top level.

  //  In a companion object
  //  We could equally have placed them in a companion object to JsonWriter.
  //  Placing instances in a companion object to the type class has special significance in Scala
  //  because it plays into something called implicit scope.

  //  implicit scope
  //  compiler searches for candidate type class instances by type. The places where the compiler searches for candidate instances is known as the implicit scope.
  //  • local or inherited definitions;
  //  • imported definitions;
  //  • definitions in the companion object of the type class or the parameter type(in this case JsonWriter or String).

  //  For our purposes, we can package type class instances in roughly four ways:
  //  1. by placing them in an object such as JsonWriterInstances;
  //   we bring instances into scope by importing them
  //  2. by placing them in a trait;
  //   we bring them into scope with inheritance
  //  3. by placing them in the companion object of the type class (JsonWriter),
  //   Approach - Interface Objects;
  //   instances are always in implicit scope, regardless of where we try to use them
  //  4. by placing them in the companion object of the parameter type (Person or String in our example)
  //   Approach - Interface Syntax (Cats use this).
  //   instances are always in implicit scope, regardless of where we try to use them

  //  It is conventional to put type class instances in a companion object (option 3 and 4 above)


}
