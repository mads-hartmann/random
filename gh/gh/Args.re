module Commands = {
  type t =
    | Help
    | PullRequests
    | Error Errors.t;
};

let parse args =>
  switch args {
  | []
  | ["help"]
  | ["-h"]
  | ["--help"] => Commands.Help
  | ["pullrequests"] => Commands.PullRequests
  | [unknown, ..._] => Commands.Error (Errors.UnknownCommand unknown)
  };
