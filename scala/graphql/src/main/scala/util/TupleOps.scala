package util

class TupleOps[T](val tuple: T) extends AnyVal {
  import TupleOps._

  /**
    * Prepends the given value to the tuple producing a tuple of arity n + 1.
    */
  def prepend[A](value: A)(implicit ao: PrependOne[A, T]): ao.Out =
    ao(value, tuple)
}

object TupleOps {

  implicit def enhanceTuple[T: Tuple](tuple: T): TupleOps[T] =
    new TupleOps(tuple)

  type PrependOneAux[A, T, O] = PrependOne[A, T] { type Out = O }

  trait PrependOne[A, T] {
    type Out
    def apply(value: A, tuple: T): Out
  }
  object PrependOne extends TuplePrependOneInstances

}
