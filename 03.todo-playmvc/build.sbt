name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)

val appDependencies = Seq(
  "com.h2database" % "h2" % "1.4.189",
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "com.typesafe.play" % "play_2.11" % "2.4.3",
  "com.typesafe.play" % "play-jdbc-api_2.11" % "2.4.3",
  "com.typesafe.play" % "play-slick_2.11" % "1.1.0"
)

libraryDependencies ++= appDependencies

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

unmanagedResourceDirectories in Test <+= baseDirectory ( _ /"target/web/public/test" )

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true