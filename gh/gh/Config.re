open Core;

type t = {access_token: string};

let from_env () :Result.t t Errors.t =>
  switch (Sys.getenv "GITHUB_OAUTH_TOKEN") {
  | Some token => Result.Ok {access_token: token}
  | None =>
    Result.Error (
      Errors.InvalidConfiguration "Missing or empty environment variable GITHUB_OAUTH_TOKEN"
    )
  };
