package com.kbudunov.lesson.core.exercise

//Функциональные комбинаторы
object Tasks extends App {

  val firstValue = Some("1")
  def getFirstValue1 = firstValue.map(e => e.toInt) //заходим в опшн, изменяем тип значения но НЕвытаскиваем
  def getFirstValue2 = firstValue.map(e => e.toInt).getOrElse(1) //явно ВЫТАСКИВАЕМ ииз опшна


  private val secondValue: Some[Some[String]] = Some(Some("1"))
  def getSecondValue1 = secondValue.map(e => e.map(e => (e.toInt * 5).toString))
  def getSecondValue2 = secondValue.flatMap(e => e.map(e => e.toInt))


  private val thirdValue = List(Some("1"), Some("2"), Some("3"))
  def getThirdValue1 = thirdValue.flatten.map(e => e.toInt).foldLeft(0)((m: Int, n: Int) => m + n)
  def getThirdValue2 = thirdValue.map(e => e.map(e => e.toInt)).flatten.sum


  private val forthValue = Some(List(Some("1"), Some("2"), Some("3")))
  def getForthValue1 = forthValue.map(l=>l.map(e=>e.map(e=>e.toInt*2)))
  def getForthValue2 = forthValue.map(l=>l.flatMap(s=>s.map(e=>e.toInt)).sum)


  val opList1: Option[List[Option[Option[String]]]] = Some(List(Some(Some("1")), Some(Some("2")), Some(Some("3")), Some(Some("4"))))
  def sumAll1: Option[Int] = opList1.map(l=>l.flatten.flatten.map(e=>e.toInt).sum)

  val opList2: Option[List[Option[Option[Int]]]]= Some(List(Some(Some(1)), Some(None), Some(Some(3)), Some(Some(4)), None))
  def sumAll2: Option[Int] = opList2.map(l => l.flatten.flatten.sum)


  private val fivethValue = Some(Some(List(Some("1"), Some("2"), Some("3"))))
  def getFivethValue = fivethValue.flatten.map(l=>l.flatten.map(e=>e.toInt).filter(e=>e % 2 == 0).sum)

  val resultList = List(1, 2, 3, 4).zip(List("a", "b", "c"))
  val numbers = List(1, 2, 3, 4, 5, 7, -8, 9, 10)
  val partitionResult = numbers.partition(_ % 2 == 0) //в левый список попадут true в правый false

  val findResult = numbers.find((i: Int) => i < 0)

  val numbers2 = numbers.drop(5)


  val lst = List(List(1, 2), List(3, 4))
  val lstRes = lst.flatMap(l=> l.map(e=>e*2))


  val opList3: Option[List[Option[Option[String]]]] = Some(List(Some(Some("1")), Some(Some("2")), Some(Some("3")), Some(Some("4"))))
  //def sumAll3: Int = opList1.flatMap(l=>l.flatMap(e=>e.flatMap(e=>e.map(e=>e.toInt)).sum))


  println(lstRes)


  val opList: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil
  val result: Option[List[Int]] = Some(opList)

}
