name := "profiller"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.inject" % "guice" % "4.0-beta",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)     

play.Project.playJavaSettings

jacoco.settings

ScctPlugin.instrumentSettings

parallelExecution in jacoco.Config := false

jacoco.excludes in jacoco.Config := Seq("*Reverse*", "Routes*", "*routes*")