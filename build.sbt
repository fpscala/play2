
name := "play2"

includeFilter in (Assets, LessKeys.less) := "*.less"
excludeFilter in (Assets, LessKeys.less) := "_*.less"

 
version := "1.0" 
      
lazy val `play2` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice ,
  "com.typesafe.scala-logging" % "scala-logging_2.12" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "ch.qos.logback" % "logback-core" % "1.1.7",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.21",
  "org.codehaus.janino" % "janino" % "3.0.7",
  //web jars
  "org.webjars" %% "webjars-play" % "2.6.1",
  "org.webjars" % "jquery" % "1.11.3",
  "org.webjars" % "knockout" % "3.3.0",
  "org.webjars" % "requirejs" % "2.2.0",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "momentjs" % "2.8.1"



)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
