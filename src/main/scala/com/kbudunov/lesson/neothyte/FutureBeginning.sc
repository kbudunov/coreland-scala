import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success}

//СУТЬ!!! Параллельные вычисления в Scala основаны на двух столбах, один из них — Future, другой — Actor.
//Future изначально входил в состав библиотеки для параллельных вычислений Akka.

//ПРОБЛЕМА!!! (Нет параллелизма)
{

  import scala.util.Try

  // Определим осмысленные синонимы:
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  // Методы-заглушки для отдельных шагов алгоритма:
  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"

  def heatWater(water: Water): Water = water.copy(temperature = 85)

  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"

  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  // Исключения, на случай если что-то пойдёт не так
  // (они понадобяться нам позже):
  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  // последовательно выполним алгоритм:
  def prepareCappuccino(): Try[Cappuccino] = for {
    ground <- Try(grind("arabica beans"))
    water <- Try(heatWater(Water(25)))
    espresso <- Try(brew(ground, water))
    foam <- Try(frothMilk("milk"))
  } yield combine(espresso, foam)


  //В Scala мы тоже можем пользоваться функциями обратного вызова через Future, но нам они не понадобятся. Есть лучшие альтернативы.

  //Всё что мы можем делать с Future в Java так это проверить завершилось оно или нет, или блокировать вычисления до завершения.
  //в Scala Future ппц сделано!!!!


  //Семантика Future******************************************************************************************************************************************************************
  //ОПРЕДЕЛЕНИЕ
  //Тип Future[T], определённый в scala.concurrent package — это тип коллекция,
  //представляющий вычисление, которое когда-нибудь закончится и вернёт значение типа
  //T. Вычисление может закончиться с ошибкой или не буть вычисленным в поставленные временные рамки.
  //Если что-то пойдёт не так, то результат будет содержать исключение.
  //Запись значения осуществляется с помощью типа Promise.
}


//Работа с Future*******************************************************************************************************************************************************

//СУЛЬ!!!!!
/*object Future {
  def apply[T](body: => T)(implicit execctx: ExecutionContext): Future[T]
}*/

//В параметр body по имени передаётся вычисление, которое будет выполняться асинхронно.
//Второй параметр — контекст вычисления, заключённый в отдельный список параметров, является имплицитным (implicit),
// это означает, что мы можем не передавать его явно, если значение с таким типом определено в той же области видимости переменных.
// В случае Future для этого мы импортируем глобальный контекст вычисления.


//СУЛЬ!!!!!!!!!!!!!!!!!!!
//Значение типа ExecutionContext это то, что нужно для выполнения асинхронных вычислений, мы можем представить что это пул потоков.

{
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future

  // Определим осмысленные синонимы:
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  // Исключения, на случай если что-то пойдёт не так (они понадобяться нам позже):
  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(2000)
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"



  //Функции обратного вызова CALLBACK ******************************************************************************************************************************************************************************************
  //Иногда, функции обратного вызова очень хорошо подходят для простых случаев.
  //Они могут вызываться на Future в виде частично определённых функций.
  //Мы можем передать функцию обратного вызова методу onSuccess и она будет вызвана,
  //только когда вычисление закончиться успешно. Функция принимает значение, которое должно когда-нибудь вернуть Future

  //ПРОБЛЕМА!!!! Depricated!!!!!!!!!
  //grind("arabica beans").onSuccess {case ground => println("okay, got my ground coffee")}
  //grind("arabica beans").onFailure

  //РЕШЕНИЕ!!! Оба этих метода объеденены .onComplete .onFailure ====== onComplete
  import scala.util.{Failure, Success}
  grind("baked beans").onComplete { //пример CALLBACK функции!!!!!!!!
    case Success(ground) => println(s"got my $ground") //частично определенные CALLBACK функции
    case Failure(ex) => println("This grinder needs a replacement, seriously!") //частично определенные CALLBACK функции
  }

  //ВНИМАНИЕ!!! используй map flatMap вместо onComplete!!!
//Преобразование Future**************************************************************************************************************************************************************
  //MAP!!!!********************************************************************************************************************************************************************
  //Эта функция выполняется как только значение Future[Water] будет успешно вычислено.
  //Однако время может пойти и по-другому пути. Если Future[Water] завершиться с ошибкой,
  //будущее для этой функции никогда не наступит.
  //Вместо этого результатом вызова будет значение типа Future[Boolean], содержащее Failure.
  val temperatureOkay: Future[Boolean] = heatWater(Water(25)).map { water =>
    println("Мы в будущем!")
    (80 to 85).contains(water.temperature)
  }

  //FLATMAP!!!************************************************************************************************************************************************************************************
  //Если одно асинхронное вычисление зависит от другого асинхронного вычисления,
  //то нам понадобится метод flatMap.
  def temperatureOkay2(water: Water): Future[Boolean] = Future {
    (80 to 85).contains(water.temperature)
  }
  val nestedFuture: Future[Future[Boolean]] = heatWater(Water(25)).map { //Future[Future[Boolean]]
    water => temperatureOkay2(water)
  }
  val flatFuture: Future[Boolean] = heatWater(Water(25)).flatMap { //так мы получим Future[Boolean]
    water => temperatureOkay2(water)
  }

  //Функция, переданная в flatMap, будет вызвана только в том случае если значение в Future[Water] будет вычислено успешно.


  //For-генераторы *********************************************************************************************************************************************************************
  //Код станет гораздо нагляднее.
  val acceptable: Future[Boolean] = for {
    heatedWater <- heatWater(Water(25))
    okay <- temperatureOkay2(heatedWater)
  } yield okay



  //Так следующее выражение будет выполняться последовательно:
  //ПРОБЛЕМА!!!
  //Если у нас есть несколько параллельных вычислений, необходимо следить
  //за тем чтобы соответствующие им Future создавались за пределами for-генратора.
  def prepareCappuccinoSequentially(): Future[Cappuccino] = {
    for { //Причина проблем - Future создавались ВНУТРИ for генератора
      ground <- grind("arabica beans") //ПЛОХО!!! Проблема 1. Вычисления выполняются последовательно (Проблема 2. если одно из них зафейлится = все зафейлится)
      water <- heatWater(Water(20)) //ПЛОХО!!! Проблема 1. Вычисления выполняются последовательно (Проблема 2. если одно из них зафейлится = все зафейлится)
      foam <- frothMilk("milk") //ПЛОХО!!! Проблема 1. Вычисления выполняются последовательно (Проблема 2. если одно из них зафейлится = все зафейлится)
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

  //РЕШЕНИЕ!!!
  //Теперь все три асинхронных вычисления начнутся одновременно.
  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabica beans") //выполняются парралельно за пределами for генератора
    val heatedWater = heatWater(Water(20)) //выполняются парралельно за пределами for генератора
    val frothedMilk = frothMilk("milk") //выполняются парралельно за пределами for генератора
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water) //намеренно внутри (чтобы гарантировать что выполниться после groundCoffee и heatedWater)
    } yield combine(espresso, foam)
  }
}































