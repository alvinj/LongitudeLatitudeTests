name := "LongitudeLatitude"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq( 
    "com.typesafe.akka" %% "akka-actor" % "2.1.2",
    "org.scala-lang" % "scala-swing" % "2.10+"
)

scalacOptions += "-deprecation"
