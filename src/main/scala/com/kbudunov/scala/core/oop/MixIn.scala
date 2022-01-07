package com.kbudunov.scala.core.oop

object MixIn extends App {
  val realBeyonce = new VerifiedTweeter("Beyonce")
  realBeyonce.tweet("Just spilled my glass of lemonade")
}

trait User {
  def username: String
}

//SELF TYPE example
//MIX-IN
trait Tweeter {
  this: User => //Members of User now are available here -> s"$username ......"
  def tweet(tweetText: String): Unit = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User { //Important. You have to note User while Tweeter needs it
  def username = s"real $username_"
}

//MIX-IN

// Because we said this: User => in trait Tweeter, now the variable username
// is in scope for the tweet methods. This also means that since VerifiedTweeter
// extends Tweeter, it must also mix-in User (using with User).
