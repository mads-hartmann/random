open Core;

open Async;

open Cohttp_async;

let handler ::body sock req => {
  let uri = Cohttp.Request.uri req;
  switch (Uri.path uri) {
  | "/test" =>
    Uri.get_query_param uri "hello" |> Option.map f::(fun v => "hello: " ^ v) |>
    Option.value default::"No param hello supplied" |> Server.respond_string
  | _ => Server.respond_string status::`Not_found "Route not found"
  }
};

let start_server port () => {
  eprintf "Listening for HTTP on port %d\n" port;
  eprintf "Try 'curl http://localhost:%d/test?hello=xyz'\n%!" port;
  Cohttp_async.Server.create on_handler_error::`Raise (Tcp.on_port port) handler >>= (
    fun _ => Deferred.never ()
  )
};
