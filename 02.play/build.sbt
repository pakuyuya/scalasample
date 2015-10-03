lazy val commonSettings = Seq(
	organization := "org.kdk",
	version := "0.01",
	scalaVersion := "2.10.4"
)

lazy val root = (project in file("."))
	.settings(commonSettings: _*)
	.enablePlugins(PlayScala)
	.settings(
		name := "hello"
	)

libraryDependencies ++= Seq(
  jdbc,
  cache
)
