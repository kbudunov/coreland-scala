package com.kbudunov.scala.core.collections

import javafx.collections.transformation.SortedList

abstract class MyOwnList[+A] {
  def head: A
  def tail: MyOwnList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyOwnList[B] //new list with this element added
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  //HOFs
  def map[B](transformer: A => B): MyOwnList[B]
  def flatMap[B](transformer: A => MyOwnList[B]): MyOwnList[B]
  def filter[B](predicate: A => Boolean): MyOwnList[A]

  def ++[B >: A](list: MyOwnList[B]): MyOwnList[B]

  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyOwnList[A]
  def zipWith[B, C](list: MyOwnList[B], zip:(A, B) => C): MyOwnList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

object Empty extends MyOwnList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyOwnList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyOwnList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyOwnList[B] = Empty
  def flatMap[B](transformer: Nothing => MyOwnList[B]): MyOwnList[B] = Empty
  def filter[B](predicate: Nothing => Boolean): MyOwnList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyOwnList[B]): MyOwnList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyOwnList[Nothing] = Empty

  override def zipWith[B, C](list: MyOwnList[B], zip: (Nothing, B) => C): MyOwnList[C] =
    if(!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

class Cons[+A](h: A, t: MyOwnList[A]) extends MyOwnList[A] {
  def head: A = h
  def tail: MyOwnList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyOwnList[B] = new Cons(element, this)
  override def printElements: String = {
    if (t.isEmpty) s"$h"
    else s"$h ${t.printElements}"
  }

  //  [1,2,3].filter(n % 2 == 0) =
  //    [2,3].filter(n % 2 == 0) =
  //      = new Cons(2, [3].filter(n % 2 == 0))
  //      = new Cons(2, Empty.filter(n % 2 == 0))
  //      = new Cons(2, Empty)
  override def filter[B](predicate: A => Boolean ): MyOwnList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  //  [1,2,3].map(n * 2)
  //      = new Cons(2, [2,3].map(n * 2))
  //      = new Cons(2, new Cons(4, [3].map(n * 2)))
  //      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
  //      = new Cons(2, new Cons(4, new Cons(6, Empty))))
  override def map[B](transformer: A => B): MyOwnList[B] =
    new Cons(transformer(h), t.map(transformer))

  //  [1,2] ++ [3,4,5]
  //    = new Cons(1, [2] ++ [3,4,5])
  //    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  //    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
  override def ++[B >: A](list: MyOwnList[B]): MyOwnList[B] = new Cons(h, t ++ list)

  //  [1,2].flatMap(n => [n, n+1])
  //    = [1,2] ++ [2].flatMap(n => [n, n+1])
  //    = [1,2] ++ [2,3] + Empty.flatMap(n => [n, n+1])
  //    = [1,2] ++ [2,3] + Empty
  //    = [1,2,2,3]

  override def flatMap[B](transformer: A => MyOwnList[B]): MyOwnList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyOwnList[A] = {
     def insert(x: A, sortedList: MyOwnList[A]): MyOwnList[A] =
       if (sortedList.isEmpty) new Cons(x, Empty)
       else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
       else new Cons(sortedList.head, insert(x, sortedList.tail))

     val sortedTail = t.sort(compare)
     insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyOwnList[B], zip: (A, B) => C): MyOwnList[C] = {
    if(!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  }

  /*
  [1,2,3].fold(0)(+) =
  = [2,3].fold(1)(+) =
  = [3].fold(3) (+)=
  = [].fold(6) (+)
   */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object MyListExample extends App {
  val strings: MyOwnList[String] = new Cons[String]("A", new Cons("B", new Cons("C", Empty)))
  val ints: MyOwnList[Int] = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  val ints2: MyOwnList[Int] = new Cons(4, new Cons(5, Empty))
  println(ints.toString)

  println(
    ints
      .map(_ * 2)
      .toString
  )

  println(
    ints
      .filter(_ % 2 == 0)
      .toString
  )

  println((ints ++ ints2).toString)
  println(ints.flatMap(elem => new Cons[Int](elem, new Cons(elem + 1, Empty))).toString)

  ints.foreach(println)

//  println(ints.zipWith[String, String](strings, _ + "-" + _))
  println(ints.fold(0)(_ + _)) //FOLD is one of cases REDUCE

}
