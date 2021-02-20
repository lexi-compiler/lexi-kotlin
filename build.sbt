import Dependencies._

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.lexi-compiler"
ThisBuild / organizationName := "lexi"

lazy val root = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "lexi",
    libraryDependencies ++= Seq(
      antlr4,
      asm,
      fastparse,
      scalaparse,
      // scala3Compiler,
      // tastyInspector,
      scalameta,
      munit % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    Compile / mainClass := Some("lexi.CLI"),
    graalVMNativeImageOptions ++= Seq(
      "--no-fallback",
      "--report-unsupported-elements-at-runtime"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
