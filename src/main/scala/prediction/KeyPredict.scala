package prediction

sealed trait KeyPredict[K] {
  def value: K
}

object KeyPredict {
  case class KeyT[T](t: T) extends KeyPredict[T] {
    override def value: T = t
  }
}

