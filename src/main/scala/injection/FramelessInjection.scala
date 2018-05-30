package injection

import java.sql.Timestamp

import frameless.Injection
import models.TypeClic

object FramelessInjection {

  implicit val typeClicInj = Injection[TypeClic, String](_.toString, TypeClic.fromString)
  implicit val tsInj = Injection[Timestamp, Long](_.getTime, new Timestamp(_))
}
