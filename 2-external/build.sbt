enablePlugins(ScalaNativePlugin)

name := "hello"
description := "hello scala.native"

scalaVersion := "2.11.8"

nativeVerbose := true

nativeClangOptions := Seq(
  "-O2",
  "-lcurl",
  "-g",
  "-v"
)
