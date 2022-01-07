package com.kbudunov.scala.core.collections

object FuncCombinatorExercises extends App {

  val firstValue = Some("1")
  def getFirstValue1: Option[Int] = ???
  def getFirstValue2: Int = ???

  private val secondValue: Some[Some[String]] = Some(Some("1"))
  def getSecondValue2: Option[Int] = ???

  private val thirdValue = List(Some("1"), Some("2"), Some("3"))
  def getThirdValue1: Int = ???

  private val forthValue: Option[List[Some[String]]] = Some(
    List(Some("1"), Some("2"), Some("3"))
  )
  def getForthValue2: Option[Int] = ???

  val opList1: Option[List[Option[Option[String]]]] = Some(
    List(Some(Some("1")), Some(Some("2")), Some(Some("3")), Some(Some("4")))
  )
  def sumAll1: Option[Int] = ???

  val opList2: Option[List[Option[Option[Int]]]] = Some(
    List(Some(Some(1)), Some(None), Some(Some(3)), Some(Some(4)), None)
  )
  def sumAll2: Option[Int] = ???

  val opList31 =
    List(Some(Some(1)), Some(None), Some(Some(3)), Some(Some(4)), None)
  val sumAll21: Seq[Int] = ???

  val numbs = Some(List(1))
//val r = numbs.flatten.sum //Some(List(1)) Flatten does not work

  private val fifthValue = Some(Some(List(Some("1"), Some("2"), Some("3"))))
  def getFifthValue = fifthValue.flatten.map(
    l => l.flatten.map(e => e.toInt).filter(e => e % 2 == 0).sum
  )
  val rt = fifthValue.flatten.map(_.flatten.map(_.toInt).sum)

  val resultList = List(1, 2, 3, 4).zip(List("a", "b", "c"))
  val numbers = List(1, 2, 3, 4, 5, 7, -8, 9, 10)
  val partitionResult = numbers.partition(_ % 2 == 0)

  val findResult = numbers.find((i: Int) => i < 0)

  val numbers2 = numbers.drop(5)

  val lst = List(List(1, 2), List(3, 4))
  val lstRes = lst.flatMap(l => l.map(e => e * 2))

  val opList3: Option[List[Option[Option[String]]]] = Some(
    List(Some(Some("1")), Some(Some("2")), Some(Some("3")), Some(Some("4")))
  )

  //def sumAll3: Int = opList1.flatMap(l=>l.flatMap(e=>e.flatMap(e=>e.map(e=>e.toInt)).sum))
  val res = opList3.map(_.flatten.flatten.map(_.toInt).sum)

  println(lstRes)

  val opList: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil
  val result: Option[List[Int]] = Some(opList)

}
