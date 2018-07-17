package util

/**
  * ApplyConverter allows generic conversion of functions of type `(T1, T2, ...) => O` to
  * `(TupleX(T1, T2, ...)) => O`.
  */
abstract class ApplyConverter[L, O] {
  type In
  def apply(f: In): L ⇒ O
}

object ApplyConverter extends ApplyConverterInstances
