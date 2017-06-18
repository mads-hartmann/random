type t =
  | MissingArgument string
  | UnknownCommand string
  | UnknownTemplate string;

let str err =>
  switch err {
  | UnknownTemplate name => "Unknown template: " ^ name
  | MissingArgument name => "Missing argument: " ^ name
  | UnknownCommand name => "Unknown command: " ^ name
  };
