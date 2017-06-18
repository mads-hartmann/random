let help = {|
Usage: new <template-name>

Options:
  -h --help         Shows this info

|};

let run () => {
  open Args.Commands;
  let args = Array.to_list Sys.argv |> List.tl;
  switch (Args.parse args) {
  | Help =>
    print_endline help;
    exit 0
  | New template => print_endline ("Creating template " ^ template)
  | Error err =>
    print_endline (Errors.str err);
    exit 1
  }
};

let add2 x => x + 2;
