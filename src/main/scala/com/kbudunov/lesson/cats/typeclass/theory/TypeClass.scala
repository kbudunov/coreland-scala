package com.kbudunov.lesson.cats.typeclass.theory

object TypeClass extends App {
  //  TypeClass allow us to extend existing libraries with new functionality,
  //  without using traditional inheritance,and without altering the original library source code.

  //Approach - Interface Objects

  import JsonWriterInstances._

  private val json = JsonInterfaceObjects.toJson(Person("Dave", "dave@example.com")) //(personWriter)


//  Interface Syntax (Cats use this)

  import JsonInterfaceSyntax._
  import JsonWriterInstances._

  private val jsonPerson: Json = Person("Dave", "dave@example.com").toJson //.toJson(personWriter)
  private val jsonString: Json = "Hello".toJson //.toJson(stringWriter)

//  The Scala standard library provides method: implicitly (It's for Debugging!!!)
//  private val personWriter: JsonWriter[Person] = implicitly[JsonWriter[Person]]
  private val jsonStr: Json = Person("Dave", "dave@example.com").toJson
  println(jsonStr)
}

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

final case object JsNull extends Json

//STAGE 1
// This is type class
trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

//STAGE 2
object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json = JsString(value)
    }
  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person): Json = JsObject(Map("name" -> JsString(value.name), "email" -> JsString(value.email)))
    }
}

//STAGE 3
//Cats provides: Interface Objects and Interface Syntax.

//Approach - Interface Objects
object JsonInterfaceObjects {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value) //it depends on type A, compiler decides what implicit to choose
}


//Approach - Interface Syntax (Cats use this)
object JsonInterfaceSyntax {

  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }

}