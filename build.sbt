name := "profiller"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.inject" % "guice" % "4.0-beta",
  "org.mindrot" % "jbcrypt" % "0.3m"
)     

play.Project.playJavaSettings
