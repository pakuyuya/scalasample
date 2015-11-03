name := "03.5.codegen"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.189",
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "org.slf4j" % "slf4j-log4j12" % "1.7.12"
)
