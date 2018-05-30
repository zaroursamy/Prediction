name := "scalaio2018"

version := "0.1"

scalaVersion := "2.11.8"

val sparkV = "2.3.0"
val framelessV = "0.6.1"

import scalariform.formatter.preferences._

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkV,
  "org.apache.spark" %% "spark-sql" % sparkV,
  "org.typelevel" %% "frameless-dataset" % framelessV

)

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)