// I tried to translate the GADT examples I have in my blog post[1] about GADTs
// from OCaml to Scala.
//
// A direct translation resulted in the code below, but I had to add two asInstanceOf
// so I don't consider this a successful translations.
//
// Any idea how to get rid of the asInstanceOf calls? Is it even possible?
//
// [1]: http://mads-hartmann.com/ocaml/2015/01/05/gadt-ocaml.html

object Main {

    sealed trait Value[A]
    final case class VBool(value: Boolean) extends Value[Boolean]
    final case class VInt(value: Int) extends Value[Int]

    sealed trait Expression[A]
    final case class Val[A](value: Value[A]) extends Expression[A]
    final case class If[A](condition: Expression[Boolean], thn: Expression[A], els: Expression[A]) extends Expression[A]
    final case class Lt[A : Ordering](left: Expression[A], right: Expression[A]) extends Expression[Boolean]

    def eval[A : Ordering](expression: Expression[A]): A = expression match {
        case Val(VBool(b)) => b
        case Val(VInt(x)) => x
        case Lt(left , right) => {
            val l: A = eval[A](left)
            val r: A = eval[A](right)
            implicitly[Ordering[A]].lt(l, r)
        }
        case If(condition, left, right) =>
            if (eval(condition)) eval(left)
            else eval(right)
    }
}
