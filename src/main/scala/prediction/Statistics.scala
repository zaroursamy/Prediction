package prediction

object Statistics {

  implicit class Stat(s: Seq[Double]) {
    def mean: Option[Double] = if (s.nonEmpty) Some(s.sum / s.size) else None
    def variance: Option[Double] = if (s.nonEmpty) Some(s.map { x => mean.map(m => math.pow(x - m, 2)).sum }.sum / s.size) else None
    def interval: Option[(Double, Double)] = for {
      moy <- mean
      std2 <- variance
    } yield (moy - 2 * math.sqrt(std2), moy + 2 * math.sqrt(std2))
  }
}