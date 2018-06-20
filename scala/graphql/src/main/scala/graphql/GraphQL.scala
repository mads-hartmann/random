sealed trait Json
final case object JNull extends Json
final case class JInt(i: Int) extends Json
final case class JFloat(f: Float) extends Json
final case class JString(s: String) extends Json
final case class JBool(b: Boolean) extends Json
final case class JArray(v: List[Json]) extends Json
final case class JObject(fields: List[(String, Json)]) extends Json

object GraphQL {

  sealed trait Type[S]

  final case class Scalar[S](
    name: String,
    serialize: S => Json
  ) extends Type[S]

  final case class Object[S](
    name: String,
    fields: List[Field[S, _]] // TODO: What do I call the use of _ here?
  ) extends Type[S]

  // I don't really care about O here I just want to tell the Scala
  // compiler that the type parameter of output and the result of the
  // resolve function should be the same type.
  final case class Field[S, O](
    name: String,
    output: Type[O],
    resolve: S => O
  )

  def toJSON[S](s: S, tpe: Type[S]): Json = tpe match {
    case Scalar(name, serialize) => serialize(s)
    case Object(name, fields) => {
      val members = fields.map { field =>
        // The reason this doesn't work is that fields has type
        // List[Field[S, Any]] as Any is the only applicable super-type
        // for all the fields.
        val json = toJSON(field.resolve(s), field.output)
        (field.name, json)
      }
      JObject(members)
    }
  }

  val int = Scalar[Int](name="Int", serialize=(i: Int) => JInt(i))

  val string = Scalar[String](name="String", serialize=(s: String) => JString(s))

}

object Example {

  case class User(name: String, id: Int)

  val Id = GraphQL.Field(
    name="id",
    output=GraphQL.int,
    resolve=(u: User) => u.id
  )

  val Name = GraphQL.Field(
    name="name",
    output=GraphQL.int,
    resolve=(u: User) => u.id
  )

  val UserType = GraphQL.Object(
    "User",
    List(Id, Name)
  )

  GraphQL.toJSON(
    User("Mads", 42),
    UserType
  )

}
