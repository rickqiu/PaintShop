name := "PaintShop"
version := "1.0"
scalaVersion := "2.11.8"

organization := "paintshop"

libraryDependencies ++= Seq(
"org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

test in assembly := {}