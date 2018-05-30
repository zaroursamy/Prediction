package jobs

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkConfig {

  val getSC: SparkConf = new SparkConf().setAppName("scalaio2018").setMaster("local[*]")

  val getSS: SparkSession = SparkSession
    .builder()
    .config(getSC)
    .getOrCreate()
}
