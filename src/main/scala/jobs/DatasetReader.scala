package jobs

import config.Path
import logger.Logger
import org.apache.spark.sql.{ DataFrame, Dataset, Encoder, SparkSession }

import scala.util.{ Failure, Success, Try }

trait DataframeReader {
  val logger: Logger
  def readDF(ss: SparkSession): Try[DataFrame]
}

object DataframeReader {

  object Implicit {
    implicit val evVisitor = new DataframeReader {
      override val logger: Logger = new Logger
      override def readDF(ss: SparkSession): Try[DataFrame] = Try(ss.read.load(Path.pathVisitor))
    }
  }
}

trait DatasetReader[T] {
  def readDS(path: String)(implicit ev: DataframeReader): Dataset[T]
}

object DatasetReader {
  def instance[T](transform: DataFrame => Dataset[T])(implicit ev: DataframeReader, ss: SparkSession, enc: Encoder[T]): DatasetReader[T] = new DatasetReader[T] {
    override def readDS(path: String)(implicit ev: DataframeReader): Dataset[T] = ev.readDF(ss).map(transform).getOrElse(ss.emptyDataset[T])
  }
}
