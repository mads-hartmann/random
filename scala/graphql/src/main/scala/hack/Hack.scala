package hack

import util.Tuple
import util.TupleOps._

// TODO
// - Why do I have to call hac3 explicitly?
// - If I remove hac3 I get a not very helpful compile error.
// - I haven't encoded the source of the field yet. I would need PrependTwoAux or something similar
//   but I don't want to do that unless I know that is the encoding I want to use.

object Hack {
  import util.ApplyConverter._

  case class MyContext(url: String)

  case class Field[Ctx, Output, Args: Tuple, Input](
      ctx: Ctx,
      args: Args,
      resolve: (Input) => Output
  )(implicit
    ev: PrependOneAux[Ctx, Args, Input])

  Field(
    ctx = MyContext("what"),
    args = ("hest", 42),
    resolve = hac3((ctx: MyContext, name: String, id: Int) => id)
  )

}
