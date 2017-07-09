name := """emarketplay"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaCore,
  evolutions,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.8.Final",
  javaJpa,
  "dom4j" % "dom4j" % "1.6.1" intransitive(),
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.typesafe.play" %% "play-mailer" % "5.0.0",
  "org.apache.poi" % "poi-ooxml" % "3.16",
  "io.jsonwebtoken" % "jjwt" % "0.7.0"
)

