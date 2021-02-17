ThisBuild / scalaVersion := "2.13.3"

lazy val root = project.in(file(".")).aggregate(graphics.js, graphics.jvm).settings(
  publishTo := Some( Resolver.file("file",  new File( "/var/www/maven" ) ) )
)

lazy val graphics = crossProject(JSPlatform, JVMPlatform).settings(
  publishTo := Some(Resolver.file("file",  new File( "/var/www/maven" )) ),
  name := "graphics",
  version := "0.1",
  organization := "ai.dragonfly.code",
  resolvers += "dragonfly.ai" at "https://code.dragonfly.ai/",
  libraryDependencies += "ai.dragonfly.code" %%% "img" % "0.2",
  scalacOptions ++= Seq("-feature"),
  mainClass in (Compile, run) := Some("ai.dragonfly.graphics.Test")
).jvmSettings().jsSettings(
  scalaJSUseMainModuleInitializer := true
)
