
object GADT {
  sealed trait Value[A]
  final case class VBool(value: Boolean) extends Value[Boolean]
  final case class VInt(value: Int) extends Value[Int]

  sealed trait Expression[A]
  final case class Val[A](value: Value[A]) extends Expression[A]
  final case class If[A](condition: Expression[Boolean], thn: Expression[A], els: Expression[A]) extends Expression[A]
  final case class Lt[A : Ordering](left: Expression[A], right: Expression[A]) extends Expression[Boolean]

  def evalValue[A](value: Value[A]): A = value match {
    case VBool(b) => b
    case VInt(i) => i
  }

  // This works - it knows that the GADT values has types Int and Boolean.
  val example1: Int = evalValue(VInt(42))
  val example2: Boolean = evalValue(VBool(false))

  def eval[A : Ordering](expression: Expression[A]): A = expression match {
    case Val(VBool(b)) => b
    case Val(VInt(x)) => x
    case Lt(left , right) => ???
    case If(condition, left, right) => ???
    // case Lt(left , right) => {
    //   val l: A = eval[A](left)
    //   val r: A = eval[A](right)
    //   implicitly[Ordering[A]].lt(l, r)
    // }
    // case If(condition, left, right) =>
    //   if (eval(condition)) eval(left)
    //   else eval(right)
  }

}
