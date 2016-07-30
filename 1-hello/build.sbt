enablePlugins(ScalaNativePlugin)

name := "sampleHello"

description := "hello scala.native"

scalaVersion := "2.11.8"

nativeVerbose := true

nativeClangOptions := Seq(
  "-O2",   // optimizer
  "-g",    // generate complete debug info
  "-v"     // show commands to run and use verbose output
)
