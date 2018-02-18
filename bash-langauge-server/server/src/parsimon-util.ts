import * as P from "parsimmon";

export function isSuccess<A>(r: P.Result<A>): r is P.Success<A> {
  return r.status
}

export function isFailure<A>(r: P.Result<A>): r is P.Failure {
  return !r.status
}

