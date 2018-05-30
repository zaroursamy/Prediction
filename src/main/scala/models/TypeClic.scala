package models

sealed trait TypeClic
case object Contact extends TypeClic
case object Info extends TypeClic

object TypeClic {


  def fromString(s:String): TypeClic = s match {
    case "Contact" => Contact
    case "Info" => Info
  }
}