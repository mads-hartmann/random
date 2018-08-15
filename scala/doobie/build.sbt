scalaVersion := "2.12.5"

scalacOptions += "-Ypartial-unification" // 2.11.9+

lazy val doobieVersion = "0.5.2"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"     % doobieVersion,
  "org.tpolecat" %% "doobie-specs2"   % doobieVersion,
  "mysql" % "mysql-connector-java" % "5.1.12"
)
