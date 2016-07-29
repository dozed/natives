package hello

import scala.scalanative._
import native._
import stdio._, stdlib._

object Main {

  def main(args: Array[String]): Unit = {


    val hello: CString = c"hello haxogreen"
    fprintf(stdout, c"hello 5th %s\n", hello.+(6))


    def foo(x: Ptr[CLong]): Unit = {
      !x = 42
    }

    val long: Ptr[CLong] = stackalloc[CLong]
    foo(long)
    fprintf(stdout, c"%d\n", !long)


    val ints: Ptr[CInt] = malloc(sizeof[CInt] * 3).cast[Ptr[CInt]]

    ints.update(0, 100)
    ints.update(1, 200)
    ints.update(2, 300)

    fprintf(stdout, c"%d\n", ints(0))
    fprintf(stdout, c"%d\n", ints(1))
    fprintf(stdout, c"%d\n", ints(2))


  }

}
