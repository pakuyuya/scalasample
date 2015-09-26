lazy val commonSettings = Seq(
	organization := "org.kdk",
	version := "0.1.0",
	scalaVersion := "2.11.4"
)

lazy val root = (project in file(".")).
	settings(
		name := "01.sbt"
	)

libraryDependencies ++= Seq(
	"org.specs2" %% "specs2-core" % "3.6.4" % "test"
)