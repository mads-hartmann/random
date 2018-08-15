# dotc other-gadt-example.scala
# dotr Evaluator
# rm *.hasTasty *.class 1

sealed trait Exp[T]
case class Lit(value: Int) extends Exp[Int]
case class Pair[A, B](fst: Exp[A], snd: Exp[B]) extends Exp[(A, B)]

object Evaluator {
  def eval[T](e: Exp[T]): T = e match {
    case Lit(x) =>
      // In this case, T = Int
      x
    case Pair(a, b) =>
      // In this case, T = (A, B) where A is the type of a and B is the type of b
      (eval(a), eval(b))
  }

  def main(args: Array[String]): Unit = {
    println(eval(Lit(1)))
    println(eval(Pair(Pair(Lit(1), Lit(2)), Lit(3))))
  }

}
