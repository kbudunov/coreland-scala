package com.kbudunov.lesson.redbook.examples.chapter.one

object SideEffectsExample {

}

//side effects for all the money
class CafeFirst {
  def buyCoffee(cc: CreditCard): Coffee = {
    val cup = Coffee()
    cc.charge(cup.price) //probleme ist here
    cup
  }
}


//better but still impure
class CafeSecond {
  def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
    val cup = Coffee()
    p.charge(cc, cup.price) //side effects is here
    cup
  }
}

//it's pure
class CafePureFirst {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = Coffee()
    (cup, Charge(cc, cup.price))
  }
}

//multiple cups with buyCoffees
class Cafe {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = ???
  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees, charges) = purchases.unzip //unzip splits a list of pairs into a pair of lists.
    (coffees, charges.reduce((c1,c2) => c1.combine(c2)))
  }

  def coalesce(charges: List[Charge]): List[Charge] =
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
}

case class Coffee(price: Double = 10)
case class Charge(cc: CreditCard, amount: Double) {
  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charges to different cards")
}

class CreditCard{
  val balance = 1000D
  def charge(price: Double) : Unit = {
    val this.balance = balance - price
  }
}

class Payments{
  def charge(cc: CreditCard, price: Double) : Unit = {
    val cc.balance = cc.balance - price
  }
}