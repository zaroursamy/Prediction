package models

import java.sql.Timestamp

case class Visit(
    idVisite: String,
    startVisit: Timestamp,
    endVisit: Timestamp,
    searches: Seq[Search])
