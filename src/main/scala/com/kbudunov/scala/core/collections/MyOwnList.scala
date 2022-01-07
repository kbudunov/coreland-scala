package com.kbudunov.scala.core.collections

abstract class MyOwnList[+A] {
  //head = first element
  //tail = remainder of the list
  //isEmpty = is this list empty
  //add(int) => new list with this element added
  //toString => return string representation

  def head: A
  def tail: MyOwnList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyOwnList[B]
//  def printElements: String
//  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyOwnList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyOwnList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyOwnList[B] = new Cons(element, Empty)
//  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyOwnList[A]) extends MyOwnList[A] {
  def head: A = h
  def tail: MyOwnList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyOwnList[B] = new Cons(element, this)
//  override def printElements: String = {
//    if(t.isEmpty) "" + h
//    else h + " " + t.printElements
//  }
}

object MyListExample extends App {
  val strings: MyOwnList[String] = new Cons[String]("54", new Cons("435", new Cons("46", Empty)))
  val ints: MyOwnList[Int] = new Cons[Int](54, new Cons(435, new Cons(46, Empty)))

//  val list = new Cons(54, new Cons(435, new Cons(46, Empty)))
//  println(list.tail.head)
//  println(list.add(1).head)
//  println(list.isEmpty)
//  println(list.toString)

}