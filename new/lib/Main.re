let help = {|
Usage: new <template-name>

Options:
  -h --help         Shows this info

|};

let run () => {
  let args = Array.to_list Sys.argv |> List.tl;
  let command = Args.parse args;

  let config_file = (Unix.getenv "HOME") ^ "/.new/config.json";
  let config = Config.read config_file;

  switch config {
    | Some cnf => print_endline cnf.templates_path
    | None => print_endline "Failed to parse config"
  };

  Args.Commands.(
    switch command {
    | Help =>
      print_endline help;
      exit 0
    | New template => print_endline ("Creating template " ^ template)
    | Error err =>
      print_endline (Errors.str err);
      exit 1
    }
  )
};

let add2 x => x + 2;
