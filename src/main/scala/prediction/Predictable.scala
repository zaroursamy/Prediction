package prediction

trait Predictable extends Product with Serializable {
  val response: Double
}