import scala.concurrent.{ExecutionContext, Future}
//Future — методы только для чтения!!! + предусмотрен очень элегантный способ обработки исключений.

//Promise - методы для чтения и записи. Promise позволяет завершить вычисление Future записью значения.
//Значение может быть записано только один раз. Как только обещание (promise) было исполнено мы не можем его изменить.
//ВАЖНО!!! нет другого способа завершения Future, кроме как через Promise!!! Это и происходит внутри метода apply для Future.
{
  //Обещая светлое будущее*****************************************************************************************************
  import concurrent.{Future, Promise}
  case class TaxCut(reduction: Int)
  // необходимо указать тип TaxCut в конструкторе:
  val taxcut = Promise[TaxCut]()
  // или подсказать тип компилятору при объявлении переменной:
  val taxcut2: Promise[TaxCut] = Promise()

  //Как только было создано значение типа Promise мы можем получить связанное с ним значение типа Future вызовом метода future на исходном значении:
  val taxcutF: Future[TaxCut] = taxcut.future //Promise основа для создания Future

  //Возвращённое значение может не совпадать с исходным значением типа Promise.
  //Но каждый вызов future на одном значении Promise будет возвращать одно и то же значение типа Future.
  //Так между значениями Promise и Future сохраняется отношение один к одному.


  //Завершение обещания***********************************************************************************************************
  //В Scala мы можем завершить Promise либо методом success либо методом failure.
  taxcut.success(TaxCut(20)) //ВАЖНО!!! После этого Promise нельзя изменить и любые попытки приведут к исключениям!!!

  import scala.concurrent.ExecutionContext.Implicits.global

  //Суть примера в том, что завершение Promise происходит в отдельном потоке.
  object Government {
    def redeemCampaignPledge(): Future[TaxCut] = {
      val p = Promise[TaxCut]()
      Future {
        println("Starting the new legislative period.")
        //Thread.sleep(2000)
        p.success(TaxCut(20))
        println("We reduced the taxes! You must reelect us!!!!1111")
      }
      p.future //запускаем фьючу как старт
    }
  }

  import scala.util.{Failure, Success}
  val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()
  println("Now that they're elected, let's see if they remember their promises...")
  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"A miracle! They really cut our taxes by $reduction percentage points!")
    case Failure(ex) =>
      println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
  }
}


{ //Нарушаем обещания************************************************************************************************
  import scala.concurrent.ExecutionContext.Implicits.global
  import concurrent.{Future, Promise}

  case class TaxCut(reduction: Int)
  case class LameExcuse(msg: String) extends Exception(msg)

  object Government1 {
    def redeemCampaignPledge(): Future[TaxCut] = {
      val p = Promise[TaxCut]()
      Future {
        println("Starting the new legislative period.")
        //Thread.sleep(2000)
        p.failure(LameExcuse("global economy crisis"))
        println("We didn't fulfill our promises, but surely they'll understand.")
      }
      p.future
    }
  }

  import scala.util.{Failure, Success}
  val taxCutF: Future[TaxCut] = Government1.redeemCampaignPledge()
  println("Now that they're elected, let's see if they remember their promises...")
  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"A miracle! They really cut our taxes by $reduction percentage points!")
    case Failure(ex) =>
      println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
  }

  //ВАЖНО!!!
  //TODO Разобраться как это работает
  //Также мы можем завершить Promise значением типа Try, вызвав метод complete.
  //Если Try содержит Success связанное Future будет завершено успешно, иначе — безуспешно.
}

{
  //Асинхронное программирование на практике
  //Вся скала в этом!!!
  //Если вы хотите повысить масштабируемость вашего приложения за счёт асинхронного выполнения кода,
  //необходимо чтобы функции на всех уровнях приложения были бы асинхронными и возвращали Future.

  //СУЛЬ!!!
  //Современные веб-фреймворки на Scala позволяют нам реагировать асинхронно,
  //обработчики возвращают ответ в виде Future[Response], вместо того, чтобы блокировать поток вычисления
  //и возвращать ответ после того как он будет сформирован. Это важно.
  //Поскольку благодаря этому веб-сервер сможет ответить на огромное число запросов с помощью
  //относительно низкого число потоков. Возвращая Future[Response] вы позволяете веб-серверу
  //наиболее эффективно использовать ресурсы пула потоков.
  //+ Также любой сервис может делать несколько запросов к базе или другим внешним веб-сервисам,



  //Неблокирующее IO***********************************************************************************************************
  //Пользуйся NIO из Java библиотек


  //Блокирующее IO
  //ПРОБЛЕМА!!! Например большинство драйверов для БД в Java используют блокирующее IO.
  // Если мы сделаем вызов такого драйвера для ответа на HTTP-запрос, это приведёт к тому что вызов будет сделан в
  // потоке веб-сервера.

  //РЕШЕНИЕ!!!!
  // Для избежания этого заключите блок кода для общения с базой в Future::
  // вернёт Future[ResultSet] или что-то вроде того:
  Future {
    //queryDB(query)
  }




  //ExecutionContext*************************************************************************************************************
  //Мы можем создать ExecutionContext с помощью ExecutionService из Java.
  //Так мы сможем настроить наш контекст выполнения специально для базы данных,
  //он не будет зависеть от остального приложения:
  import java.util.concurrent.Executors
  import concurrent.ExecutionContext

  val executorService = Executors.newFixedThreadPool(4)
  val executionContext = ExecutionContext.fromExecutorService(executorService)


}


























