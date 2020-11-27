package com.kbudunov.lesson.core.types

object Variance extends App {
  class Grandparent
  class Parent extends Grandparent
  class Child extends Parent

  class InvariantClass[A] //type A only!
  class CovariantClass[+A] //type A and their kids
  class ContravariantClass[-A] //type A and their parents

  class VarianceExamples {
    def invarMethod(x: InvariantClass[Parent]): Unit = {}
    def covarMethod(x: CovariantClass[Parent]): Unit = {}
    def contraMethod(x: ContravariantClass[Parent]): Unit = {}

//    invarMethod(new InvariantClass[Child]) // ERROR - won't compile
    invarMethod(new InvariantClass[Parent]) // success
//    invarMethod(new InvariantClass[Grandparent]) // ERROR - won't compile
    covarMethod(new CovariantClass[Child]) // success
    covarMethod(new CovariantClass[Parent]) // success
//    covarMethod(new CovariantClass[Grandparent]) // ERROR - won't compile
//    contraMethod(new ContravariantClass[Child]) // ERROR - won't compile
    contraMethod(new ContravariantClass[Parent]) // success
    contraMethod(new ContravariantClass[Grandparent]) // success
  }
}
