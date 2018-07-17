package graphql

abstract class Arg[T] {
  def name: String
  def apply[O](f: T => O): O
}

object Arg {

  def int(n: String): Arg[Int] = new Arg[Int] {
    def name: String = n
    def apply[O](f: Int => O): O = ??? // This would look up in an arg map.
  }

  def string(n: String): Arg[String] = new Arg[String] {
    def name: String = n
    def apply[O](f: String => O): O = ??? // This would look up in an arg map.
  }

}

trait ArgList[L] {

  def args: List[Arg[_]] // TODO: Use existential type instead?
  def apply[A](f: L => A): A
}

object ArgList {
  implicit def fromTuple2[A, B](
      t: Tuple2[Arg[A], Arg[B]]): ArgList[Tuple2[A, B]] = ???
}

object What {

  val x = (Arg.int("id"), Arg.string("name"))

  ArgList.fromTuple2(x).apply { case (id, name) => 42 }

}

/**
  * If I follow the akka approach I'd need something like the following.
  * However, Akka has a fixed return type of `Route` so their approach isn't directly applicable.
  *
  * - Convert a (Arg[A], Arg[B], ...) into XXX[(A, B, ...)]
  *
  *
  * - Convert a function (A,B,..) => O to a Function1[TupleN, O]
  *   See ApplyConverter in akka.http.scaladsl.server.util
  *
  *
  * val x = (Arg.int("id"), Arg.string("name")) // (Arg[Int], Arg[String])
  * x { (id, name) => ??? } // x is converted to an Arg[(Int, String)]
  *                         // (Int, String) => T is converted to Function1[(Int,String), T] so it matches apply
  *
  */
