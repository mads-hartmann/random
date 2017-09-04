open Core;

type t = {
  access_token: string,
  username: string
};

let get_env env_name =>
  switch (Sys.getenv env_name) {
  | Some value => Result.Ok value
  | None =>
    Result.Error (
      Errors.InvalidConfiguration (
        Printf.sprintf "Missing or empty environment variable %s" env_name
      )
    )
  };

let from_env () :Result.t t Errors.t => {
  let environment =
    Result.all [get_env "GITHUB_OAUTH_TOKEN", get_env "GITHUB_USERNAME"];
  Base.Result.bind
    environment
    f::(
      fun envs =>
        switch envs {
        | [token, username] => Result.Ok {access_token: token, username}
        | _ =>
          Result.Error (
            Errors.InvalidConfiguration "Failed to read environment variables"
          )
        }
    )
};
