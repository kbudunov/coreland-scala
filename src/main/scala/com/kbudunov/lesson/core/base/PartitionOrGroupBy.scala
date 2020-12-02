package com.kbudunov.lesson.core.base

object PartitionOrGroupBy extends App {
  val x = List(15, 10, 5, 8, 20, 12)

  val groups = x.groupBy(_ > 10) // HashMap(false -> List(10, 5, 8), true -> List(15, 20, 12))
  println(groups)
  val trues = groups(true)
  val falses = groups(false)

  val p = x.partition(_ > 10) //(List(15, 20, 12),List(10, 5, 8))
  println(p)

  val s = x.span(_ < 20) //(List(15, 10, 5, 8),List(20, 12)) до первого вхождения 20, потом все во вторую группу
  println(s)

  val sp = x.splitAt(2) //(List(15, 10),List(5, 8, 20, 12)) берет первые 2 остальные во вторую группу
  println(sp)

}
