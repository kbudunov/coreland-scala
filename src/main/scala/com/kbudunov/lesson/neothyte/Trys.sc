

//TRY похож на OPTION!!!

//ТАК НЕПРИНЯТО!!!
/*
ПРОБЛЕМА (МИНУСЫ)************************************************************************************************************************************************************************************
        Неподходит для парралельных вычислений.
        ПРИМЕР: В Акторе(другом) случилось исключение и мы его так неотловим
*/
{
  case class Customer(age: Int)
  class Cigarettes
  case class UnderAgeException(message: String) extends Exception(message)
  def buyCigarettes(customer: Customer): Cigarettes =
    if (customer.age < 16)
      throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
    else new Cigarettes

  val youngCustomer = Customer(15)
  try { //обработка в Java стиле!!!
    buyCigarettes(youngCustomer)
    "Yo, here are your cancer sticks! Happy smoking'!"
  } catch {
    case UnderAgeException(msg) => msg
  }
}

// РЕШЕНИЕ!!!************************************************************************************************************************************************************************************
// В Scala мы воспользуемся специальным типом,
// который говорит о том, что вычисление может привести к ошибке.
// Try включён в Scala с версии 2.10
// Either как альтернатива Try


//Try[A] описывает вычисление, которое может вернуть значение типа A,
//если оно завершится успешно или некоторый объект из класса Throwable, если что-то пойдёт не так.

//ДВА ВАРИАНТА Try************************************************************************************************************************************************************************************
//Success[A] ----- Success
//Failure[A] ----- Throwable

//Если нам известно, что вычисление может закончиться с ошибкой,
//то мы можем просто использовать Try[A] в качестве типа возвращаемого значения
{
  import java.net.URL

  import scala.util.Try
  def parseURL(url: String): Try[URL] = Try(new URL(url)) //Try[URL]!!!!

  //Ошибок нет - Success[URL]
  println(parseURL("http://danielwestheide.com"))

  //Исключение Failure[A] вместо A будет завернуто исключение
  println(parseURL("garbage"))

  //Использование значений типа Try
  // isSuccess, get, getOrElse - как и в Option
  //val url = parseURL(Console.readLine("URL: ")) getOrElse new URL("http://duckduckgo.com")

  //MAP, FLATMAP******************************************************************************************************************************************************************************
  //как и в Option
  println(parseURL("http://danielwestheide.com").map(_.getProtocol)) //маппим из Success[A] в Success[B]
  // results in Success("http")
  println(parseURL("garbage").map(_.getProtocol)) //маппим из Failure[A] в Failure[B]
  // results in Failure(java.net.MalformedURLException: no protocol: garbage)

  //СУТЬ!!! Try это специальный Option!!!
  //Если мы проводим несколько вызовов map на функциях с исключениями,
  //то мы получим вложенные значения типа Try
  //ПРОБЛЕМА!!! Try[Try[Try[InputStream]]] - нужно схлопнуть!!!
  import java.io.InputStream
  def inputStreamForURL(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u =>
    Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }

  //РЕШЕНИЕ!!!
  def inputStreamForURLFlatMap(url: String): Try[InputStream] =
    parseURL(url).flatMap { u => //первый
      Try(u.openConnection()).flatMap(conn => //второй flatMap
        Try(conn.getInputStream))
    }


  //FILTER и FOREACH *************************************************************************************************************************************************************
  //работаю также как и в Option

  //Тут отличае от Option!!! В  Опшине None просто непопадает в результирующий список
  def parseHttpURL(url: String) = parseURL(url).filter(_.getProtocol == "http")

  //ВАРИАНТ 1. Метод filter вернёт Failure, если значение уже содержит Failure
  println(parseHttpURL("garbage")) // Failure(java.net.MalformedURLException: no protocol: garbage)

  //ВАРИАНТ 2. Или если предикат вернёт ложь (тогда на выходе мы получим исключение NoSuchElementException).
  println(parseHttpURL("ftp://mirror.netcologne.de/apache.org")) // Failure(java.util.NoSuchElementException: Predicate does not hold for ftp://mirror.netcologne.de/apache.org)

  //ВАРИАНТ 3. Если значение Try, содержит Success[A] и предикат вернёт истину, тогда на выходе будет то же самое значение:
  println(parseHttpURL("http://apache.openmirror.de")) // Success(http://apache.openmirror.de)

  //Функция, переданная в foreach, выполняется только в том случае, если Try содержит Success[A].
  parseHttpURL("http://danielwestheide.com").foreach(println)


  //For-генераторы ***************************************************************************************************************************************************************************************
  import scala.io.Source
  def getURLContent(url: String): Try[Iterator[String]] =
    for { //Наглядная работа c Try вместо flatMap (like as Option)
      url <- parseURL(url) //Извлекаем из ПЕРВОГО Try (чтобы перети к следущей функции нам нужен Success)
      connection <- Try(url.openConnection()) //Извлекаем из ВТОРОГО Try (чтобы перети к следущей функции нам нужен Success)
      is <- Try(connection.getInputStream) //Извлекаем из ТРЕТЬЕГО Try
      source = Source.fromInputStream(is)
    } yield source.getLines()


  //Сопоставление с образцом**********************************************************************************************************************************************************
  //Проверяем, что же произошло с вычислением
  import scala.util.Success
  import scala.util.Failure
  getURLContent("http://danielwestheide.com/foobar") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
  }


  //Восстановление после ошибки***********************************************************************************************************************************************************
  //Метод recover принимает частично определённую функцию и возвращает Try
  //ВАРИАНТ 1. Ошибок неслучилось. Если значение, на котором был вызван метод recover,
  //завершится успешно, будет возвращён результат Success[A],

  //ВАРИАНТ 2. Ексепшн. Будет вызвана частично определённая функция.
  //Если она определена для исключения, которое произошло, будет вызвана функция
  //и результат будет обвёрнут в Success. ВАЖНО!!!!!!!!
  import java.net.MalformedURLException
  import java.io.FileNotFoundException
  val content = getURLContent("garbage") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist") //Будет вызвана частично определённая функция.
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")//Будет вызвана частично определённая функция.
    case _ => Iterator("An unexpected error has occurred. We are so sorry!")
  }

  content.get.foreach(println) //Теперь мы можем спокойно извлечь значение из Try[Iterator[String]]

  //ВЫВОД:
  //Если возникло исключение recover перехватывает его и закладывает
  //в ответ результат частичной функции заварачивая все это в Success


  //В Try. Так же как и в Option, в Try есть метод orElse. Стоит присмотреться к методам transform и recoverWith !!!!
}
























