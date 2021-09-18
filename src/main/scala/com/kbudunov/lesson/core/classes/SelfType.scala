package com.kbudunov.lesson.core.classes

object SelfType extends App {
  val realBeyonce = new VerifiedTweeter("Beyoncé")
  realBeyonce.tweet("Just spilled my glass of lemonade")

}

trait User {
  def username: String
}

trait Tweeter {
  this: User => //Тут подтягивается то что есть в User -> поэтому username доступна
  def tweet(tweetText: String): Unit = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User { //Тут обязательно нужен User т.к. Tweeter его требует
  def username = s"real $username_"
}

// Because we said this: User => in trait Tweeter, now the variable username
// is in scope for the tweet method. This also means that since VerifiedTweeter
// extends Tweeter, it must also mix-in User (using with User).
