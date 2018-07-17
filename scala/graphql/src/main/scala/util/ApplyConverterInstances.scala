package util

// format: off

private[util] abstract class ApplyConverterInstances {

  type Aux[T, A, In0] = ApplyConverter[T, A] { type In = In0 }

  implicit def whatever[T1, T2, T3, A, In](f: In)(implicit ev: Aux[Tuple3[T1, T2, T3], A, In]) = ev.apply(f)

  implicit def hac1[T1, A]: ApplyConverter[Tuple1[T1], A] { type In = (T1) ⇒ A } = new ApplyConverter[Tuple1[T1], A] {
    type In = (T1) ⇒ A
    def apply(fn: In): (Tuple1[T1]) ⇒ A = {
      case Tuple1(t1) ⇒ fn(t1)
    }
  }

  implicit def hac2[T1, T2, A]: ApplyConverter[Tuple2[T1, T2], A] { type In = (T1, T2) ⇒ A } = new ApplyConverter[Tuple2[T1, T2], A] {
    type In = (T1, T2) ⇒ A
    def apply(fn: In): (Tuple2[T1, T2]) ⇒ A = {
      case Tuple2(t1, t2) ⇒ fn(t1, t2)
    }
  }
  implicit def hac3[T1, T2, T3, A]: ApplyConverter[Tuple3[T1, T2, T3], A] { type In = (T1, T2, T3) ⇒ A } = new ApplyConverter[Tuple3[T1, T2, T3], A] {
    type In = (T1, T2, T3) ⇒ A
    def apply(fn: In): (Tuple3[T1, T2, T3]) ⇒ A = {
      case Tuple3(t1, t2, t3) ⇒ fn(t1, t2, t3)
    }
  }
  implicit def hac4[T1, T2, T3, T4, A]: ApplyConverter[Tuple4[T1, T2, T3, T4], A] { type In = (T1, T2, T3, T4) ⇒ A } = new ApplyConverter[Tuple4[T1, T2, T3, T4], A] {
    type In = (T1, T2, T3, T4) ⇒ A
    def apply(fn: In): (Tuple4[T1, T2, T3, T4]) ⇒ A = {
      case Tuple4(t1, t2, t3, t4) ⇒ fn(t1, t2, t3, t4)
    }
  }
  implicit def hac5[T1, T2, T3, T4, T5, A]: ApplyConverter[Tuple5[T1, T2, T3, T4, T5], A] { type In = (T1, T2, T3, T4, T5) ⇒ A } = new ApplyConverter[Tuple5[T1, T2, T3, T4, T5], A] {
    type In = (T1, T2, T3, T4, T5) ⇒ A
    def apply(fn: In): (Tuple5[T1, T2, T3, T4, T5]) ⇒ A = {
      case Tuple5(t1, t2, t3, t4, t5) ⇒ fn(t1, t2, t3, t4, t5)
    }
  }
  implicit def hac6[T1, T2, T3, T4, T5, T6, A]: ApplyConverter[Tuple6[T1, T2, T3, T4, T5, T6], A] { type In = (T1, T2, T3, T4, T5, T6) ⇒ A } = new ApplyConverter[Tuple6[T1, T2, T3, T4, T5, T6], A] {
    type In = (T1, T2, T3, T4, T5, T6) ⇒ A
    def apply(fn: In): (Tuple6[T1, T2, T3, T4, T5, T6]) ⇒ A = {
      case Tuple6(t1, t2, t3, t4, t5, t6) ⇒ fn(t1, t2, t3, t4, t5, t6)
    }
  }
  implicit def hac7[T1, T2, T3, T4, T5, T6, T7, A]: ApplyConverter[Tuple7[T1, T2, T3, T4, T5, T6, T7], A] { type In = (T1, T2, T3, T4, T5, T6, T7) ⇒ A } = new ApplyConverter[Tuple7[T1, T2, T3, T4, T5, T6, T7], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7) ⇒ A
    def apply(fn: In): (Tuple7[T1, T2, T3, T4, T5, T6, T7]) ⇒ A = {
      case Tuple7(t1, t2, t3, t4, t5, t6, t7) ⇒ fn(t1, t2, t3, t4, t5, t6, t7)
    }
  }
  implicit def hac8[T1, T2, T3, T4, T5, T6, T7, T8, A]: ApplyConverter[Tuple8[T1, T2, T3, T4, T5, T6, T7, T8], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8) ⇒ A } = new ApplyConverter[Tuple8[T1, T2, T3, T4, T5, T6, T7, T8], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8) ⇒ A
    def apply(fn: In): (Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]) ⇒ A = {
      case Tuple8(t1, t2, t3, t4, t5, t6, t7, t8) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8)
    }
  }
  implicit def hac9[T1, T2, T3, T4, T5, T6, T7, T8, T9, A]: ApplyConverter[Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9) ⇒ A } = new ApplyConverter[Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9) ⇒ A
    def apply(fn: In): (Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]) ⇒ A = {
      case Tuple9(t1, t2, t3, t4, t5, t6, t7, t8, t9) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9)
    }
  }
  implicit def hac10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, A]: ApplyConverter[Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) ⇒ A } = new ApplyConverter[Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) ⇒ A
    def apply(fn: In): (Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]) ⇒ A = {
      case Tuple10(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10)
    }
  }
  implicit def hac11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, A]: ApplyConverter[Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) ⇒ A } = new ApplyConverter[Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) ⇒ A
    def apply(fn: In): (Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]) ⇒ A = {
      case Tuple11(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)
    }
  }
  implicit def hac12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, A]: ApplyConverter[Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) ⇒ A } = new ApplyConverter[Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) ⇒ A
    def apply(fn: In): (Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]) ⇒ A = {
      case Tuple12(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12)
    }
  }
  implicit def hac13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, A]: ApplyConverter[Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) ⇒ A } = new ApplyConverter[Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) ⇒ A
    def apply(fn: In): (Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]) ⇒ A = {
      case Tuple13(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13)
    }
  }
  implicit def hac14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, A]: ApplyConverter[Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) ⇒ A } = new ApplyConverter[Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) ⇒ A
    def apply(fn: In): (Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]) ⇒ A = {
      case Tuple14(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)
    }
  }
  implicit def hac15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, A]: ApplyConverter[Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) ⇒ A } = new ApplyConverter[Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) ⇒ A
    def apply(fn: In): (Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]) ⇒ A = {
      case Tuple15(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15)
    }
  }
  implicit def hac16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, A]: ApplyConverter[Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) ⇒ A } = new ApplyConverter[Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) ⇒ A
    def apply(fn: In): (Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]) ⇒ A = {
      case Tuple16(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16)
    }
  }
  implicit def hac17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, A]: ApplyConverter[Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) ⇒ A } = new ApplyConverter[Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) ⇒ A
    def apply(fn: In): (Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]) ⇒ A = {
      case Tuple17(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17)
    }
  }
  implicit def hac18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, A]: ApplyConverter[Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) ⇒ A } = new ApplyConverter[Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) ⇒ A
    def apply(fn: In): (Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]) ⇒ A = {
      case Tuple18(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18)
    }
  }
  implicit def hac19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, A]: ApplyConverter[Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) ⇒ A } = new ApplyConverter[Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) ⇒ A
    def apply(fn: In): (Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]) ⇒ A = {
      case Tuple19(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19)
    }
  }
  implicit def hac20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, A]: ApplyConverter[Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) ⇒ A } = new ApplyConverter[Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) ⇒ A
    def apply(fn: In): (Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]) ⇒ A = {
      case Tuple20(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20)
    }
  }
  implicit def hac21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A]: ApplyConverter[Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) ⇒ A } = new ApplyConverter[Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) ⇒ A
    def apply(fn: In): (Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]) ⇒ A = {
      case Tuple21(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21)
    }
  }
  implicit def hac22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, A]: ApplyConverter[Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22], A] { type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) ⇒ A } = new ApplyConverter[Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22], A] {
    type In = (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) ⇒ A
    def apply(fn: In): (Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]) ⇒ A = {
      case Tuple22(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22) ⇒ fn(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22)
    }
  }
}
