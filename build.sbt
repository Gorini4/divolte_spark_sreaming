name := "divolte_spark_example"

version := "0.1"

scalaVersion := "2.12.11"

val sparkVersion = "2.4.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion  % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %%  "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %%  "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-avro" % sparkVersion,
  "joda-time" % "joda-time" % "2.10.5"
)
