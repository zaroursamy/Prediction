package models

case class Profile(
    idProfile: String,
    clics: Seq[Clic]) {
  def isFamous: Boolean = clics.size > 20
}
