import scala.io.Source
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.avro._
import org.apache.spark.sql.streaming.Trigger

object DivolteExample extends App {

  val spark = SparkSession.builder()
    .master("local[2]")
    .appName("DivolteExample")
    .getOrCreate()

  import spark.implicits._

  val jsonFormatSchema = Source.fromResource("divolte_schema.avsc").getLines.mkString

  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "divolte")
    .load()

  val output = df
    .select(from_avro('value, jsonFormatSchema) as 'divolte_event)
    .select("divolte_event.*")

  output
    .writeStream
    .format("console")
    .trigger(Trigger.ProcessingTime(5000))
    .start()
    .awaitTermination()

}
