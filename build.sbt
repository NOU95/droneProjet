name := "drone"

version := "0.1"

scalaVersion := "2.12.10"


libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.7.0"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.7.0"



// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.0-alpha1"
// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.0-alpha1"


// A library for generating fake data.
libraryDependencies += "com.github.pjfanning" % "scala-faker_2.12" % "0.5.2"
