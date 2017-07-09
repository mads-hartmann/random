val dottyVersion = "0.1.2-RC1"

lazy val root = (project in file(".")).
  settings(
    name := "dotty-simple",
    version := "0.1",
    scalaVersion := dottyVersion,
    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.1.0"
  )

assemblyMergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last contains "ScalaRunTime" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
