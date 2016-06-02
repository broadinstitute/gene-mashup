name := "gene-mashup"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).settings(
  scalaVersion := "2.11.8",
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
).enablePlugins(PlayScala)
