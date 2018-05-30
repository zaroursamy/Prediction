package main

import java.sql.Timestamp

import frameless.{TypedDataset, TypedEncoder}
import jobs.SparkConfig
import logger.Logger
import models._
import org.apache.spark.sql.{Dataset, Encoders, SQLContext, SparkSession}
import prediction.Prediction

object Main {




  def main(args: Array[String]): Unit = {
    implicit val ss: SparkSession = SparkConfig.getSS
    implicit val sqlContext: SQLContext = ss.sqlContext
    import ss.implicits._
    implicit val logger: Logger = new Logger

    val mockVisitor = Seq(
      Visitor("0", "os", Seq(Visit("0", new Timestamp(0L), new Timestamp(0L), Seq(Search("0", Query("", "", "", ""), Seq(Profile("0",Seq(Clic(Contact, new Timestamp(0L))))))))))
    )

    import injection.FramelessInjection._
    val ds: Dataset[Visitor] = TypedDataset.create[Visitor](mockVisitor).dataset

    ds.show
    Prediction.predict(ds)(v => v -> v.os :: Nil).show
  }


}
