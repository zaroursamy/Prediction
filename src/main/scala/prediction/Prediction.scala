package prediction

import org.apache.spark.sql.{ Dataset, Encoder, Encoders, SparkSession }
import prediction.KeyPredict.KeyT

import scala.reflect.ClassTag

trait Prediction[K <: KeyPredict[_]] {
  def key: K
  val maxInterval: Option[Double]
  val minInterval: Option[Double]
  val mean: Option[Double]
  val variance: Option[Double]
  val sum: Double
}

object Prediction {

  import Statistics.Stat

  def predict[T <: { def response: Double }, K](ds: Dataset[T])(f: T => Seq[(T, K)])(implicit ss: SparkSession, encT: Encoder[T], encK: Encoder[K]): Dataset[Prediction[KeyT[K]]] = {
    implicit val tupleEnc = Encoders.tuple[T, K](encT, encK)
    implicit val encP = Encoders.kryo[Prediction[KeyT[K]]]
    import ss.implicits._
    ds
      .flatMap(f)
      .groupByKey(_._2)
      .mapGroups({
        case (k, it) =>

          val distrib: Seq[Double] = it.map(_._1.response).toSeq

          new Prediction[KeyT[K]] {
            override def key: KeyT[K] = KeyT(k)
            override val maxInterval: Option[Double] = distrib.interval.map(_._2)
            override val minInterval: Option[Double] = distrib.interval.map(_._1)
            override val mean: Option[Double] = distrib.mean
            override val variance: Option[Double] = distrib.variance
            override val sum: Double = distrib.sum
          }
      })
  }
}
