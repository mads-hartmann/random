package graphql

object Schema {

  /** A type represents a node in your GraphQL schema.
    *
    * Implementation note:
    *
    * A GADT is used here to enforce that types are nullable by default
    * and should be marked non-nullable in the Schema using [[NonNull]].
    */
  sealed trait Type[S]

  /** A [[Scalar]] is a leaf-node in your GraphQL schema. It has a [[name]] and
    * a way to [[serialize]] the underlying [[S]] value to a [[Json]] value.
    */
  final case class Scalar[S](
      name: String,
      serialize: S => Json
  ) extends Type[Option[S]]

  object Scalar {
    val int: Scalar[Int] =
      Scalar[Int](
        name = "Int",
        serialize = JInt.apply
      )

    val string: Scalar[String] =
      Scalar[String](
        name = "String",
        serialize = JString.apply
      )
  }

  /**
    * An [[Object]] is a non-leaf node in your GraphQL schema. It has a [[name]]
    * and a list of [[Field]]s.
    */
  final case class Object[S](
      name: String,
      fields: List[Field[S]]
  ) extends Type[Option[S]]

  /**
    * [[NonNull]] lifts a nullable [[Type]] into a non-nullable one.
    */
  final case class NonNull[S](tpe: Type[Option[S]]) extends Type[S]

  /**
    * A [[Field]] represents an edge in your GraphQL schema going from a
    * [[Type]] with an underlying source [[S]] to a [[Type]] with
    * underlying source [[Field#Out]].
    *
    * Implementation note:
    *
    * The type member Out is an existential type that is solely used to ensure
    * that the following requirement holds:
    *
    *   > The type of a field agrees with the return type of the resolve
    *     function.
    *
    * A type member is used over a type parameter as a type parameter would
    * caused issues when Fields were put in a List.
    * see commit a732a8494608fda82064963a45adfab117fa0da8
    */
  trait Field[S] {
    type Out
    def name: String
    def output: Type[Out]
    def resolve: S => Out
  }

  object Field {

    // Convention used to convert a type parameter to a type member.
    // This is mainly so I don't mis-spell `type Out` all over the place.
    type Aux[S, O] = Field[S] { type Out = O }

    def apply[S, O](
        name: String,
        output: Type[O],
        resolve: S => O
    ): Aux[S, O] = {
      val n = name
      val o = output
      val r = resolve
      new Field[S] {
        type Out = O
        val name: String = n
        val output: Type[O] = o
        val resolve: S => O = r
      }
    }
  }

  /**
    * Given a source value of type [[S]] and a [[Type]] with source
    * type [[S]] traverse the graph to produce a [[Json]] value.
    *
    * Implementation note:
    *
    * As [[Type]] is a GADT the Scala compiler can use the constructors to
    * infer information about the concrete type [[S]]. See implementation
    * for comments.
    *
    * I had to split this into two methods, [[toJSON]] and [[toJSONOpt]] as
    * the Scala compiler would otherwise infer [[S]] to be Any which resulted
    * in runtime exceptions in some cases.
    *   See commit 60d3c32a3580f4f460a58831bbedbf96a2388f29 for the original
    * implementation which had problems.
    */
  def toJSON[S](source: S, tpe: Type[S]): Json = tpe match {

    // Here the Scala compiler knows that S really is an Option[_] as
    // Scalar[S] and Object[S] both have type Type[Option[S]]
    case Scalar(_, serialize) => toJSONOpt(source, tpe)
    case Object(_, fields)    => toJSONOpt(source, tpe)

    // Here the Scala compiler should know that S is just a simple type (not an
    // Option) and that inner has type Type[Option[S]].
    case NonNull(inner) => toJSONOpt(Some(source), inner)

  }

  private def toJSONOpt[S](source: Option[S], tpe: Type[Option[S]]): Json =
    tpe match {
      case Scalar(_, serialize) =>
        source match {
          case None    => JNull
          case Some(s) => serialize(s)
        }
      case Object(_, fields) =>
        source match {
          case Some(s) => {
            val members = fields.map { field =>
              val json = toJSON(field.resolve(s), field.output)
              (field.name, json)
            }
            JObject(members)
          }
          case None => JNull
        }
      // I really wish the compiler could look at the GADT and see that the
      // only two relevant cases for Type[Option[_]] is Scalar and Object.
      case NonNull(_) =>
        throw new Exception(
          """
          |So this is a bit embarrassing.
          |The compiler should have stopped you from constructing a schema that
          |contains a non-nullable optional value but somehow you manged.
        """.stripMargin
        )
    }

}
