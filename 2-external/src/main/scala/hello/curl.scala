package hello

import scala.scalanative._, native._, stdio._, stdlib._

@extern
object curl {

  type CURLcode = CInt
  type CURLoption = CInt

  def curl_easy_init(): Ptr[_] = extern
  def curl_easy_cleanup(curl: Ptr[_]): Unit = extern
  def curl_easy_setopt(curl: Ptr[_], option: CURLoption, value: Ptr[_]): CURLcode = extern
  def curl_easy_perform(curl: Ptr[_]): CURLcode = extern

}

// https://gist.github.com/jseidl/3218673
object CURLOpts {

  final val CURLOPT_URL: CInt                 = 10002
  final val CURLOPT_WRITEFUNCTION: CInt       = 20011
  final val CURLOPT_WRITEDATA: CInt           = 10001

}
