package models

case class Visitor(
    idVisitor: String,
    os: String,
    visites: Seq[Visit]) /*extends Predictable*/ {

  def searches: Seq[Search] = visites.flatMap(_.searches)
  def queries: Seq[Query] = searches.map(_.query)
  def profiles: Seq[Profile] = searches.flatMap(_.profiles)
  def clics: Seq[Clic] = profiles.flatMap(_.clics)

  def response: Double = clics.collect { case Clic(Contact, _) => 1d }.sum
}
