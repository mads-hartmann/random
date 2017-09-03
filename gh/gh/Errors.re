type t =
  | UnknownCommand string
  | AuthenticationError string
  | InvalidConfiguration string
  | HttpError int string;

let to_str err =>
  switch err {
  | UnknownCommand name => "Unknown command: " ^ name
  | AuthenticationError err => "Authentication failed: " ^ err
  | InvalidConfiguration err => "Invalid configuration: " ^ err
  | HttpError code err =>
    "HTPP request failed (" ^ string_of_int code ^ "): " ^ err
  };
