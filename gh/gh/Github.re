open Lwt;

open Cohttp;

open Cohttp_lwt_unix;

module PullRequest = {
  type t = {
    title: string,
    reviewer: string,
    author: string
  };
  let of_json (json: Yojson.Basic.json) :t => {
    open Yojson.Basic.Util;
    let title = json |> member "title" |> to_string;
    {title, reviewer: "WAT", author: "TEST"}
  };
};

let query username =>
  Printf.sprintf
    {|{
  user(login:\"%s\") {
    pullRequests(
      first:100,
      states: [OPEN],
      orderBy: {
        field:UPDATED_AT,
        direction:DESC
      }
    ) {
      edges {
        node {
          title
          reviewRequests(first:100){
            nodes {
              reviewer {
                login
              }
            }
          }
          author {
            login
          }
          id
        }
      }
    }
  }
}|}
    username;

let parse (json: Yojson.Basic.json) :list PullRequest.t =>
  Yojson.Basic.Util.(
    json
    |> member "data"
    |> member "user"
    |> member "pullRequests"
    |> member "edges"
    |> to_list
    |> List.map (fun x => PullRequest.of_json (member "node" x))
  );

let pullrequests config :Lwt.t (Core.Result.t (list PullRequest.t) Errors.t) => {
  let headers =
    Header.init_with "Authorization" ("token " ^ Config.(config.access_token))
    |> (fun h => Header.add h "User-Agent" "github-pull-requests")
    |> (fun h => Header.add h "Content-Type" "application/json");
  let uri = Uri.of_string "https://api.github.com/graphql";
  let req =
    Cohttp_lwt_body.of_string (
      GraphQL.Query.of_string (query Config.(config.username))
    );
  Client.post ::headers body::req uri
  >>= (
    fun (resp, body) => {
      let code = resp |> Response.status |> Code.code_of_status;
      switch code {
      | 401 =>
        Lwt.return (
          Core.Result.Error (
            Errors.AuthenticationError "Failed authentication"
          )
        )
      | 200 =>
        Cohttp_lwt_body.to_string body
        >|= (
          fun body => Core.Result.Ok (parse (Yojson.Basic.from_string body))
        )
      | error_code =>
        Lwt.return (Core.Result.Error (Errors.HttpError error_code "what"))
      }
    }
  )
};
