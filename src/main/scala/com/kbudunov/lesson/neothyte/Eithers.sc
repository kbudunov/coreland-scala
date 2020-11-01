import java.net.URL

import scala.io.Source
//ВЫВОД!!! Для понимания кода Scala для версий ниже 2.10, там где он использовался для обработки исключений.

//Во первых, до появления Try для обработки исключений использовался тип Either и не все разработчики перешли на новый способ.
//Поэтому нам стоит разобраться в тонкостях и этого типа.
// На практике Try и Either дополняют друг друга


//Как Try и Option тип Either является контейнером.
//Either[A, B] может содержать значение типа A или значение типа B. В этом отличие от Tuple[A, B], который одновременно содержит два значения, типов A и B.

//ТАК ПРИНЯТО!!!
//по соглашению Left отвечает за ошибки/исключения, а Right — за успешно вычисленное значение.


//Создание значения типа Either*********************************************************************************************************************************************
{
  import java.net.URL
  import scala.io.Source

  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!") //Руками заполняем
    else
      Right(Source.fromURL(url)) //Руками заполняем

  println(getContent(new URL("http://danielwestheide.com")))
  println(getContent(new URL("https://plus.google.com")))


  //Использование Either**********************************************************************************************************************
  //Некоторые совсем простые вещи работают в Either точно так же как и в Try.
  //У нас есть методы isLeft и isRight.
  //Сопоставление с образцом
  getContent(new URL("http://google.com")) match {
    case Left(msg) => println(msg)
    case Right(source) => source.getLines.foreach(println)
  }
}





{
  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!") //Руками заполняем
    else
      Right(Source.fromURL(url)) //Руками заполняем

  //Проекции***********************************************************************************************************************************
  //Важно!!! с Either нельзя работать как с коллекциями
  //Это решение вызвано тем, что Either не должно отдавать предпочтение одной или другой альтернативе.

  //ВЫВОД!!! .right или .left это просто обращение к тому что лежит в Either
  //Обратите внимание на то, что метод map определён на проекциях,
  // а не на самом типе Either, но он возвращает значение типа Either, а не проекции.

  //МИНУСЫ - если мы хотим вызвать несколько методов map, flatMap и других методов,
  //нам придётся каждый раз указывать какую проекцию мы хотим использовать.


  //ВАРИАНТ 1. RightProjection
  //Если оно содержит Right, то значение будет преобразовано, если оно содержит Left, значение останется без изменений.

  // content содержит Right со строчками из Source, который был получен с помощью getContent ПРЕОБРАЗУЕТСЯ!!!
  val moreContentR: Either[String, Iterator[String]] =
  getContent(new URL("http://google.com")).right.map(_.getLines) //СУТЬ!!! .right мы говорим если там в Right тогда выплняй преобразования

  // moreContent содержит Left, полученный из getContent БЕЗ ИЗМЕНЕНИЙ!!!
  val contentR: Either[String, Iterator[String]] =
  getContent(new URL("http://danielwestheide.com")).right.map(_.getLines()) //СУТЬ!!! .right мы говорим если там в Right тогда выплняй преобразования

  println(contentR)
  println(moreContentR)


  //ВАРИАНТ 2. LeftProjection
  //если Either содержит Left, результат будет преобразован, а в случае Right оставлен без изменений!!!

  // content содержит Right с Source, в том виде, в котором он был получен из getContent ПРЕОБРАЗУЕТСЯ!!!
  val moreContentL: Either[Iterator[String], Source] =
  getContent(new URL("http://google.com")).left.map(Iterator(_)) //СУТЬ!!! .left мы говорим если там в Right тогда выплняй преобразования

  // moreContent содержит Left с msg, полученым из getContent в Iterator'е БЕЗ ИЗМЕНЕНИЙ!!!
  val contentL: Either[Iterator[String], Source] =
  getContent(new URL("http://danielwestheide.com")).left.map(Iterator(_)) //СУТЬ!!! .left мы говорим если там в Right тогда выплняй преобразования

  println(contentL)
  println(moreContentL)
}










{
  //Метод flatMap *************************************************************************************************
  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!") //Руками заполняем
    else
      Right(Source.fromURL(url)) //Руками заполняем


  val part5 = new URL("http://t.co/UR1aalX4")
  val part6 = new URL("http://t.co/6wlKwTmu")

  //ПРОБЛЕМА Right(Right(0))
  val content = getContent(part5).right.map(a =>
    getContent(part6).right.map(b =>
      (a.getLines().size + b.getLines().size) / 2))
  println(content)

  //РЕШЕНИЕ flatMap --- Right(0)
  //Если мы вызовем flatMap на RightProjection, то мы полуим более приятный результат.
  //Это приведёт к тому что, мы избавимся от Right во внутреннем Either.

  val content2 = getContent(part5).right.flatMap(a =>
    getContent(part6).right.map(b =>
      (a.getLines().size + b.getLines().size) / 2))

  println(content2)
}



{
  //For-генераторы *************************************************************************************************
  //ВНИМАНИЕ!!! это будет не так элегантно, придётся иметь дело с корявыми костылями.
  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!") //Руками заполняем
    else
      Right(Source.fromURL(url)) //Руками заполняем


  //много чего, нет времени понимать
  //ВЫВОД!!! не используй

}

{
  //ОБРАБОТКА КОЛЛЕКЦИЙ
  //Обычно, Either хорошо подходит для работы с коллекциями, если в какой-то момент что-то может пойти не так,
  //но при этом не возникает ошибка, исключающая дальнейшую обработку элементов коллекции.
  type Citizen = String
  case class BlackListedResource(url: URL, visitors: Set[Citizen])

  val blacklist = List(
    BlackListedResource(new URL("https://google.com"), Set("John Doe", "Johanna Doe")),
    BlackListedResource(new URL("http://yahoo.com"), Set.empty),
    BlackListedResource(new URL("https://maps.google.com"), Set("John Doe")),
    BlackListedResource(new URL("http://plus.google.com"), Set.empty)
  )

  val checkedBlacklist: List[Either[URL, Set[Citizen]]] =
    blacklist.map(resource =>
      if (resource.visitors.isEmpty) Left(resource.url)
      else Right(resource.visitors))

  val suspiciousResources = checkedBlacklist.flatMap(_.left.toOption) //отбираем подозрительные адреса
  val problemCitizens = checkedBlacklist.flatMap(_.right.toOption).flatten.toSet //отбираем подохзрительных пользователей

  println(suspiciousResources)
  println(problemCitizens)

}





















