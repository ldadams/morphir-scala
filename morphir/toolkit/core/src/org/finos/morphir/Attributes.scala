package org.finos.morphir

sealed trait Attributes extends Product with Serializable { self =>
  def empty: Attributes = Attributes.empty
}
object Attributes {
  val empty: Attributes = Empty

  case object Empty extends Attributes
}
