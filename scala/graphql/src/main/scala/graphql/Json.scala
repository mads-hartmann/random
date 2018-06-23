package graphql

/**
  * Not really interesting - I just needed a simple JSON ADT for the prototype
  * and didn't want to include a library for it.
  */
sealed trait Json
final case object JNull extends Json
final case class JInt(i: Int) extends Json
final case class JFloat(f: Float) extends Json
final case class JString(s: String) extends Json
final case class JBool(b: Boolean) extends Json
final case class JArray(v: List[Json]) extends Json
final case class JObject(fields: List[(String, Json)]) extends Json
