enablePlugins(ScalaNativePlugin)

name := "sampleLibrary"

description := "hello scala.native"

scalaVersion := "2.11.8"

// make sure you checked out and patched scalaz sources (via: checkout-scalaz.sh)
lazy val scalaz = ProjectRef(file("scalaz"), "coreJVM")

lazy val root = project.in(file("."))
  .dependsOn(scalaz)

nativeVerbose := true

nativeClangOptions := Seq(
  "-O2"
)
