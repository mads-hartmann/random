open Core;

open Async;

let main () =>
  Command.async
    summary::"Start a hello world Async server"
    Command.Spec.(
      empty +> flag "-p" (optional_with_default 8080 int) doc::"int Source port to listen on"
    )
    Hello.start_server |> Command.run;

main ();
