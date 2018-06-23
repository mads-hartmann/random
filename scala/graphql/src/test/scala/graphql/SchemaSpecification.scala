package graphql

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
    "Scalars" - {
      // TODO: This tests fails with java.lang.Integer cannot be cast to scala.runtime.Nothing$
      // TODO: If you remove the Some wrapping you get a very unhelpful error message - can we fix that?
      assert(Schema.toJSON(Some(42), Schema.Scalar.int) == JInt(42))
      assert(
        Schema.toJSON(Some("hello"), Schema.Scalar.string) == JString("hello"))
    }
  }

}
