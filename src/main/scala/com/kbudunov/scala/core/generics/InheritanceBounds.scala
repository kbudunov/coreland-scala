package com.kbudunov.scala.core.generics

object InheritanceBounds extends App {

  class Grandparent
  class Parent extends Grandparent
  class Child extends Parent

  class UpperBounder[A <: Parent] //A must be subtype of Parent
  class LowerBounder[A >: Parent] //A must be supertype of Parent
  class LowerAndUpperBounders[A >: Child <: Grandparent] //The type A has both an upper and lower bound.

//  def invarMethodGrandparent(x: UpperBounder[Grandparent]): Unit = {} //it won't compile!
  def invarMethodParent(x: UpperBounder[Parent]): Unit = {}
  def invarMethodChild(x: UpperBounder[Child]): Unit = {}

  //UPPER BOUND
//upperBoundCheck(new Grandparent) //it won't compile!
  upperBoundCheck(new Parent)
  upperBoundCheck(new Child)

  //LOWER BOUND
  lowerBoundCheck(new Grandparent)
  lowerBoundCheck(new Parent)
  //lowerBoundCheck(new Child) //it won't compile!

  //UPPERandLOWERE BOUNDS
  upperAndLowerBoundCheck(new Grandparent)
  upperAndLowerBoundCheck(new Parent)
  upperAndLowerBoundCheck(new Child)

  def upperBoundCheck[A <: Parent](x: A): Unit = {
    println("A <: Parent")
  }

  def lowerBoundCheck[A >: Parent](x: A): Unit = {
    println("A >: Parent")
  }

  def upperAndLowerBoundCheck[A >: Child <: Grandparent](x: A): Unit = {
    println("A >: Parent <: Grandparent")
  }

}
