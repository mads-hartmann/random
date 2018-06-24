package graphql

import graphql.Schema.Type
import org.scalatest.{FreeSpec, Matchers}

class SchemaSpecification extends FreeSpec with Matchers {

  "Scalars" - {

    "int" in {
      assert(Schema.Scalar.int.serialize(42) == JInt(42))
    }

    "string" in {
      assert(Schema.Scalar.string.serialize("hello") == JString("hello"))
    }

  }

  "Schema.toJSON" - {
    "Scalars" in {
      // TODO: This tests fails with java.lang.Integer cannot be cast to scala.runtime.Nothing$
      // TODO: If you remove the Some wrapping you get a very unhelpful error message - can we fix that?
      assert(Schema.toJSON(Some(42), Schema.Scalar.int) == JInt(42))
      assert(
        Schema.toJSON(Some("hello"), Schema.Scalar.string) == JString("hello"))
    }

    "Object" in {
      case class User(id: Int, name: String)
      val userT = Schema.Object(
        name = "User",
        fields = List(
          Schema.Field("id", Schema.Scalar.int, (user: User) => Some(user.id)),
          Schema.Field("name",
                       Schema.Scalar.string,
                       (user: User) => Some(user.name))
        )
      )
      val json = Schema.toJSON(Some(User(42, "Mads")), userT)
      val expected = JObject(
        List(
          ("id", JInt(42)),
          ("name", JString("Mads"))
        ))

      assert(json == expected)

    }

    "NonNull" in {
      assert(Schema.toJSON(42, Schema.NonNull(Schema.Scalar.int)) == JInt(42))
      assert(
        Schema.toJSON("hello", Schema.NonNull(Schema.Scalar.string)) == JString(
          "hello"))
    }
  }

}
