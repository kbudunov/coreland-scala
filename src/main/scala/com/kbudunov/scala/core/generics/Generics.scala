package com.kbudunov.scala.core.generics

object Generics extends App {

  class MyList[A] {
    def add[B >: A](element: B): MyList[B] = ???
    //A = cat
    //B = Animal
  }

  class MyMap[Key, Value]

  val list = new MyList[Int]
  val listS = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: List[A] = ???
  }

  val emptyListOfInt = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1 YES - List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList1: CovariantList[Animal] = new CovariantList[Animal]
  val animalList2: CovariantList[Animal] = new CovariantList[Cat]
//  val animalList: CovariantList[Cat] = new CovariantList[Animal]
  //animalList.add(Dog()) ???

  //2 NO - INVARIANCE
  class InvariantList[A]
//  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
//  val invariantAnimalList: InvariantList[Cat] = new InvariantList[Animal]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3 Hell, no! CONTRAVARIANCE
  class ContravariantList[-A]
//  val contravariantList: ContravariantList[Animal] = new ContravariantList[Cat]
  val contravariantList1: ContravariantList[Cat] = new ContravariantList[Animal]
  val contravariantList2: ContravariantList[Animal] = new ContravariantList[Animal]


  //UPPER-BOUNDED types
  class Cage[A <: Animal](animal: A) //A is subtype of animal
  val cage = new Cage(new Dog)

  class Car
//  val newCage = new Cage(new Car)

  //LOWER-BOUNDED types
  class Cage1[A >: Animal](animal: A) //A is supertype of animal
  val cage1 = new Cage1(new Dog)


  //expend MyOwnList to be Genetic

}
