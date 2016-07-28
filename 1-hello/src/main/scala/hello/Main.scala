package hello

import scala.scalanative._
import native._
import stdio._, stdlib._

object Main {

  def main(args: Array[String]): Unit = {

    fprintf(stderr, c"Hello, World!\n")

    // Raytracer.run(6)

  }

}
