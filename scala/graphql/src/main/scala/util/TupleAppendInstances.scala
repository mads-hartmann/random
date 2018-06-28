package util

// format: off

import util.TupleOps.PrependOne

private[util] abstract class TuplePrependOneInstances {
  type Aux[A, T, Out0] = PrependOne[A, T] { type Out = Out0 }

  implicit def prepend0[T1]: Aux[Unit, T1, Tuple1[T1]] =
    new PrependOne[Unit, T1] {
      type Out = Tuple1[T1]
      def apply(value: Unit, tuple: T1): Tuple1[T1] = Tuple1(tuple)
    }

  implicit def prepend1[L, T1]: Aux[L, Tuple1[T1], Tuple2[L, T1]] =
    new PrependOne[L, Tuple1[T1]] {
      type Out = Tuple2[L, T1]
      def apply(value: L, tuple: Tuple1[T1]): Tuple2[L, T1] = Tuple2(value, tuple._1)
    }

  implicit def prepend2[L, T1, T2]: Aux[L, Tuple2[T1, T2], Tuple3[L, T1, T2]] =
    new PrependOne[L, Tuple2[T1, T2]] {
      type Out = Tuple3[L, T1, T2]
      def apply(value: L, tuple: Tuple2[T1, T2]): Tuple3[L, T1, T2] = Tuple3(value, tuple._1, tuple._2)
    }

  implicit def prepend3[L, T1, T2, T3]: Aux[L, Tuple3[T1, T2, T3], Tuple4[L, T1, T2, T3]] =
    new PrependOne[L, Tuple3[T1, T2, T3]] {
      type Out = Tuple4[L, T1, T2, T3]
      def apply(value: L, tuple: Tuple3[T1, T2, T3]): Tuple4[L, T1, T2, T3] = Tuple4(value, tuple._1, tuple._2, tuple._3)
    }

  implicit def prepend4[L, T1, T2, T3, T4]: Aux[L, Tuple4[T1, T2, T3, T4], Tuple5[L, T1, T2, T3, T4]] =
    new PrependOne[L, Tuple4[T1, T2, T3, T4]] {
      type Out = Tuple5[L, T1, T2, T3, T4]
      def apply(value: L, tuple: Tuple4[T1, T2, T3, T4]): Tuple5[L, T1, T2, T3, T4] = Tuple5(value, tuple._1, tuple._2, tuple._3, tuple._4)
    }

  implicit def prepend5[L, T1, T2, T3, T4, T5]: Aux[L, Tuple5[T1, T2, T3, T4, T5], Tuple6[L, T1, T2, T3, T4, T5]] =
    new PrependOne[L, Tuple5[T1, T2, T3, T4, T5]] {
      type Out = Tuple6[L, T1, T2, T3, T4, T5]
      def apply(value: L, tuple: Tuple5[T1, T2, T3, T4, T5]): Tuple6[L, T1, T2, T3, T4, T5] = Tuple6(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5)
    }

  implicit def prepend6[L, T1, T2, T3, T4, T5, T6]: Aux[L, Tuple6[T1, T2, T3, T4, T5, T6], Tuple7[L, T1, T2, T3, T4, T5, T6]] =
    new PrependOne[L, Tuple6[T1, T2, T3, T4, T5, T6]] {
      type Out = Tuple7[L, T1, T2, T3, T4, T5, T6]
      def apply(value: L, tuple: Tuple6[T1, T2, T3, T4, T5, T6]): Tuple7[L, T1, T2, T3, T4, T5, T6] = Tuple7(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6)
    }

  implicit def prepend7[L, T1, T2, T3, T4, T5, T6, T7]: Aux[L, Tuple7[T1, T2, T3, T4, T5, T6, T7], Tuple8[L, T1, T2, T3, T4, T5, T6, T7]] =
    new PrependOne[L, Tuple7[T1, T2, T3, T4, T5, T6, T7]] {
      type Out = Tuple8[L, T1, T2, T3, T4, T5, T6, T7]
      def apply(value: L, tuple: Tuple7[T1, T2, T3, T4, T5, T6, T7]): Tuple8[L, T1, T2, T3, T4, T5, T6, T7] = Tuple8(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7)
    }

  implicit def prepend8[L, T1, T2, T3, T4, T5, T6, T7, T8]: Aux[L, Tuple8[T1, T2, T3, T4, T5, T6, T7, T8], Tuple9[L, T1, T2, T3, T4, T5, T6, T7, T8]] =
    new PrependOne[L, Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]] {
      type Out = Tuple9[L, T1, T2, T3, T4, T5, T6, T7, T8]
      def apply(value: L, tuple: Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]): Tuple9[L, T1, T2, T3, T4, T5, T6, T7, T8] = Tuple9(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8)
    }

  implicit def prepend9[L, T1, T2, T3, T4, T5, T6, T7, T8, T9]: Aux[L, Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9], Tuple10[L, T1, T2, T3, T4, T5, T6, T7, T8, T9]] =
    new PrependOne[L, Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]] {
      type Out = Tuple10[L, T1, T2, T3, T4, T5, T6, T7, T8, T9]
      def apply(value: L, tuple: Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Tuple10[L, T1, T2, T3, T4, T5, T6, T7, T8, T9] = Tuple10(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9)
    }

  implicit def prepend10[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]: Aux[L, Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], Tuple11[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]] =
    new PrependOne[L, Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]] {
      type Out = Tuple11[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]
      def apply(value: L, tuple: Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]): Tuple11[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10] = Tuple11(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10)
    }

  implicit def prepend11[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]: Aux[L, Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11], Tuple12[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]] =
    new PrependOne[L, Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]] {
      type Out = Tuple12[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]
      def apply(value: L, tuple: Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]): Tuple12[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11] = Tuple12(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11)
    }

  implicit def prepend12[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]: Aux[L, Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12], Tuple13[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]] =
    new PrependOne[L, Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]] {
      type Out = Tuple13[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]
      def apply(value: L, tuple: Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]): Tuple13[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12] = Tuple13(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12)
    }

  implicit def prepend13[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]: Aux[L, Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13], Tuple14[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]] =
    new PrependOne[L, Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]] {
      type Out = Tuple14[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]
      def apply(value: L, tuple: Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]): Tuple14[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13] = Tuple14(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13)
    }

  implicit def prepend14[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]: Aux[L, Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14], Tuple15[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]] =
    new PrependOne[L, Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]] {
      type Out = Tuple15[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]
      def apply(value: L, tuple: Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]): Tuple15[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14] = Tuple15(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14)
    }

  implicit def prepend15[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]: Aux[L, Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15], Tuple16[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]] =
    new PrependOne[L, Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]] {
      type Out = Tuple16[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]
      def apply(value: L, tuple: Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]): Tuple16[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15] = Tuple16(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15)
    }

  implicit def prepend16[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]: Aux[L, Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16], Tuple17[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]] =
    new PrependOne[L, Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]] {
      type Out = Tuple17[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]
      def apply(value: L, tuple: Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]): Tuple17[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16] = Tuple17(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16)
    }

  implicit def prepend17[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]: Aux[L, Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17], Tuple18[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]] =
    new PrependOne[L, Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]] {
      type Out = Tuple18[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]
      def apply(value: L, tuple: Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]): Tuple18[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17] = Tuple18(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17)
    }

  implicit def prepend18[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]: Aux[L, Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18], Tuple19[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]] =
    new PrependOne[L, Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]] {
      type Out = Tuple19[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]
      def apply(value: L, tuple: Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]): Tuple19[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18] = Tuple19(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18)
    }

  implicit def prepend19[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]: Aux[L, Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19], Tuple20[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]] =
    new PrependOne[L, Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]] {
      type Out = Tuple20[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]
      def apply(value: L, tuple: Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]): Tuple20[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19] = Tuple20(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19)
    }

  implicit def prepend20[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]: Aux[L, Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20], Tuple21[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]] =
    new PrependOne[L, Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]] {
      type Out = Tuple21[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]
      def apply(value: L, tuple: Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]): Tuple21[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20] = Tuple21(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20)
    }

  implicit def prepend21[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]: Aux[L, Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21], Tuple22[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]] =
    new PrependOne[L, Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]] {
      type Out = Tuple22[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]
      def apply(value: L, tuple: Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]): Tuple22[L, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21] = Tuple22(value, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21)
    }
}
