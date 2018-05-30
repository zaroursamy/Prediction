package jobs

import config.Path._
import models.Visitor
import org.apache.spark.sql.{ Dataset, SparkSession }

object HDFSReader {
  import DataframeReader.Implicit._

  def visitorDS(implicit ss: SparkSession): Dataset[Visitor] = {
    import ss.implicits._
    DatasetReader.instance(_.as[Visitor]).readDS(pathVisitor)
  }
}
