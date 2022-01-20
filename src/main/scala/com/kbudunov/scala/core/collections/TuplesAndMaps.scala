package com.kbudunov.scala.core.collections

object TuplesAndMaps extends App                                                                                                          {

  //tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello")
  val aTuple2 = (2, "Hello")


  println(aTuple.copy(_2 = "hi"))
  println(aTuple.swap)  //("Hello", 2)


}
