package hello

import scala.scalanative._, native._, stdio._, stdlib._


object Main {

  def downloadFile(url: CString, outfilename: CString): Unit = {

    val ch = curl.curl_easy_init()

    val fp: Ptr[FILE] = fopen(outfilename, c"wb")

    curl.curl_easy_setopt(ch, CURLOpts.CURLOPT_URL, url)
    curl.curl_easy_setopt(ch, CURLOpts.CURLOPT_WRITEDATA, fp)

    val res = curl.curl_easy_perform(ch)

    fclose(fp)

    curl.curl_easy_cleanup(ch)

  }


  def main(args: Array[String]): Unit = {

    //  Unresolved dependencies:
    //    `@java.io.PushbackReader::init_class.java.io.Reader`
    //    `@java.io.PushbackReader::unread_i32_unit`
    //    `@java.io.PushbackReader`
    //    `@java.net.URL::init_class.java.lang.String`
    //    `@java.net.URL::openStream_class.java.io.InputStream`
    //    `@java.net.URL`
    // val content = io.Source.fromURL("https://www.google.de", "ISO-8859-1").mkString

    downloadFile(c"https://google.com", c"/tmp/test")


  }
}
