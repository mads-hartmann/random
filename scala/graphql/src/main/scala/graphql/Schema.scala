package graphql

object Schema {

  /** A type represents a node in your GraphQL schema.
    *
    * Implementation note:
    *
    * Trying to use a GADT here to enforce that types are nullable by default
    * and should be marked non-nullable in the Schema using [[NonNull]].
    *
    * I haven't been successful - see [[toJSON]].
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

  //
  // What I tried to do:
  //
  // Use a GADT to model a Type such that each of the constructors _should_ be
  // able to give the Scala compiler some more knowledge of the type S; see
  // comments in each of the pattern matches below for details.
  //
  // What I got:
  //
  // Code that compiles but breaks at runtime with an exception:
  //
  //     java.lang.Integer cannot be cast to scala.runtime.Nothing$
  //
  // See SchemaSpecification.scala for concrete example.
  //
  // Help:
  //
  // How can I get this to either:
  //
  // - Not break during runtime or
  // - Not compile in a meaningful way.
  //
  def toJSON[S](source: S, tpe: Type[S]): Json = tpe match {

    case Scalar(_, serialize) =>
      // Here the Scala compiler should know that S really is an Option[_] as
      // Scalar[S] is a Type[Option[S]]
      source match {
        case None    => JNull
        case Some(s) => serialize(s)
      }

    case Object(_, fields) =>
      // Here the Scala compiler should know that S really is an Option[_] as
      // Object[S] is a Type[Option[S]]
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

    case NonNull(inner) =>
      // Here the Scala compiler should know that S is just a simple type (not an Option)
      // and that inner has type Type[Option[S]].
      toJSON(Some(source), inner)

  }

}
