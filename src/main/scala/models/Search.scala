package models

case class Search(
    idSearch: String,
    query: Query,
    profiles: Seq[Profile])