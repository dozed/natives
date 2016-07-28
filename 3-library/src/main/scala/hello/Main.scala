package hello

import scala.scalanative._, native._, stdio._, stdlib._

import scalaz._, Scalaz._


object GPIOWorld {

  sealed trait PowerState
  object PowerState {
    case object Powered extends PowerState
    case object Unpowered extends PowerState
  }

  sealed trait GPIOState
  object GPIOState {
    case object On extends GPIOState
    case object Off extends GPIOState
  }

  case class HW(
    vendor: String,
    powerState: PowerState,
    gpio: GPIO
  )

  case class GPIO(
    pin1: GPIOState,
    pin2: GPIOState,
    pin3: GPIOState,
    pin4: GPIOState
  )


  // define a few state modifications

  val switchOn: State[HW, Unit] =
    State.modify[HW](hw => hw.copy(powerState = PowerState.Powered))

  val switchOff: State[HW, Unit] =
    State.modify[HW](hw => hw.copy(powerState = PowerState.Unpowered))

  def delay(ms: Int): State[HW, Unit] = State.state(())  // nop

  def setGPIO(i: Int, state: GPIOState): State[HW, Unit] = State.modify { hw =>
    i match {
      case 1 => hw.copy(gpio = hw.gpio.copy(pin1 = state))
      case 2 => hw.copy(gpio = hw.gpio.copy(pin2 = state))
      case 3 => hw.copy(gpio = hw.gpio.copy(pin3 = state))
      case 4 => hw.copy(gpio = hw.gpio.copy(pin4 = state))
      case _ => hw // just return the current value
    }
  }

  def getGPIO1: State[HW, GPIOState] = State.gets(_.gpio.pin1)
  def getGPIO2: State[HW, GPIOState] = State.gets(_.gpio.pin2)
  def getGPIO3: State[HW, GPIOState] = State.gets(_.gpio.pin3)
  def getGPIO4: State[HW, GPIOState] = State.gets(_.gpio.pin4)

  // re-use previous getters
  def getGPIO(i: Int): State[HW, Option[GPIOState]] = {
    i match {
      case 1 => getGPIO1.map(_.some)
      case 2 => getGPIO2.map(_.some)
      case 3 => getGPIO3.map(_.some)
      case 4 => getGPIO4.map(_.some)
      case _ => State.state(none[GPIOState])
    }
  }

  // turn on pins 2 and 4, wait 1000ms, return pin states
  val togglePins42: State[HW, (GPIOState, GPIOState)] = for {
    _ <- setGPIO(2, GPIOState.On)
    _ <- setGPIO(4, GPIOState.On)
    _ <- delay(1000)
    pin2 <- getGPIO2
    pin4 <- getGPIO4
  } yield (pin2, pin4)


}


object Main {

  import GPIOWorld._


  def main(args: Array[String]): Unit = {

    // our state modification program
    val prog1: State[HW, (GPIOState, GPIOState)] = for {
      _ <- switchOn
      x <- togglePins42
    } yield x


    // create hw
    val hw: HW = HW(
      vendor = "Raspberry Pi Foundation",
      powerState = PowerState.Unpowered,
      gpio = GPIO(
        pin1 = GPIOState.Off,
        pin2 = GPIOState.Off,
        pin3 = GPIOState.Off,
        pin4 = GPIOState.Off
      )
    )


    // run the program
    val (hw2, (pin2, pin4)): (HW, (GPIOState, GPIOState)) = prog1.run(hw)

    // print state and result
    println(pin2)
    println(pin4)
    println(hw.powerState)
    println(hw2.powerState)


  }
}
