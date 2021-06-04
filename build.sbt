ThisBuild / scalaVersion := "2.13.3"
ThisBuild / resolvers += "dragonfly.ai" at "https://code.dragonfly.ai/"
ThisBuild / version := "0.102"
ThisBuild / organization := "ai.dragonfly.code"
ThisBuild / scalacOptions ++= Seq("-feature", "-deprecation")
ThisBuild / publishTo := Some(Resolver.file("file",  new File("/var/www/maven")))

lazy val root = project.in(file(".")).aggregate(graphics.js, graphics.jvm)

lazy val graphics = crossProject(JSPlatform, JVMPlatform).settings(
  name := "graphics",
  libraryDependencies += "ai.dragonfly.code" %%% "img" % "0.203",
  mainClass in (Compile, run) := Some("ai.dragonfly.graphics.Test")
).jvmSettings().jsSettings(
  scalaJSUseMainModuleInitializer := true
)
