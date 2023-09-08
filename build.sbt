ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "Steps",
    idePackagePrefix := Some("pl.ayeo.steps")
  )

libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"