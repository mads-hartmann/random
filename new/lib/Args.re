module Commands = {
  type t =
    | Help
    | New string
    | Error Errors.t;
};

let parse_command_new args =>
  switch args {
  | [template, ..._] => Commands.New template
  | [] => Commands.Error (Errors.MissingArgument "template-name")
  };

let parse args =>
  switch args {
  | []
  | ["help"] => Commands.Help
  | ["new", ...new_args] => parse_command_new new_args
  | [unknown, ..._] => Commands.Error (Errors.UnknownCommand unknown)
  };
