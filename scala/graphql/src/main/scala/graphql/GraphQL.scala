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
    fields: List[Field[S]]
  ) extends Type[S]

  trait Field[S] {
    // We use a type memeber here rather than a type parameter as we're
    // want to use it as an existential type.
    //   Had I used a type parameter and created a List[Field] it would
    // unifty the type.
    type O
    def name: String
    def output: Type[O]
    def resolve: S => O
  }

  object Field {

    // Convention used to convert a type parameter to a type member.
    type Aux[S, O0] = Field[S] { type O = O0 }

    def apply[S, O0](
      name: String,
      output: Type[O0],
      resolve: S => O0
    ): Aux[S, O0] = {
      val n = name
      val o = output
      val r = resolve
      new Field[S] {
        type O = O0
        val name: String = n
        val output: Type[O] = o
        val resolve: S => O = r
      }
    }
  }

  def toJSON[S](s: S, tpe: Type[S]): Json = tpe match {
    case Scalar(name, serialize) => serialize(s)
    case Object(name, fields) => {
      val members = fields.map { field =>
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
    output=GraphQL.string,
    resolve=(u: User) => u.name
  )

  val UserType = GraphQL.Object(
    "User",
    List(Id, Name)
  )

  def main(args: Array[String]) = {
    val x = GraphQL.toJSON(
      User("Mads", 42),
      UserType
    )
    println(x)
  }


}
