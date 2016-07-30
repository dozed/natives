package hello

import scala.scalanative._
import native._
import stdio._, stdlib._

object Main {

  @struct
  class Pi(n: Int = 0) {
    @inline def `++`: Pi = {
      new Pi(n+1)
    }

    @inline def value: Double = {
      4 * (0 to n).foldLeft(0.0) { case (acc, i) =>
        val sig = if (i % 2 == 0) 1.0 else -1.0
        acc + sig / (i*2 + 1)
      }
    }
  }

  def showPi(π: Ptr[Pi]): Unit = {
      fprintf(stdout, c"%f\n", (!π ++).value)
  }


  def main(args: Array[String]): Unit = {


    // CString
    val hello: CString = c"hello haxogreen"
    fprintf(stdout, c"hello 5th %s\n", hello.+(6))


    // allocate a Pi
    val π = stackalloc[Pi]

    !π = new Pi(42)

    showPi(π)


    // malloc
    val ints: Ptr[CInt] = malloc(sizeof[CInt] * 3).cast[Ptr[CInt]]

    ints.update(0, 100)
    ints.update(1, 200)
    ints.update(2, 300)

    fprintf(stdout, c"%d\n", ints(0))
    fprintf(stdout, c"%d\n", ints(1))
    fprintf(stdout, c"%d\n", ints(2))

  }

}
