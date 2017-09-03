let usage = {|
Usage: gh <command>

Options:
  -h --help         Shows this info

|};

let run () => {
  let args = Array.to_list Sys.argv |> List.tl;
  let command = Args.parse args;
  let config = Config.from_env ();
  switch (config, command) {
  | (_, Args.Commands.Help) =>
    print_endline usage;
    exit 0
  | (_, Args.Commands.Error err)
  | (Result.Error err, _) =>
    print_endline (Errors.to_str err);
    exit 1
  | (Result.Ok config, Args.Commands.PullRequests) =>
    switch (Lwt_main.run (Github.pullrequests config)) {
    | Core.Result.Error err => print_endline (Errors.to_str err)
    | Core.Result.Ok pullrequests => Print.pullrequests pullrequests
    };
    exit 0
  }
};

run ();
