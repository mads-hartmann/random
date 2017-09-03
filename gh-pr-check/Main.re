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

module GraphQL = {
  module Query = {
    let of_string (query: string) :string => {
      let newline = Str.regexp "\n";
      let tab = Str.regexp "\t";
      let cleaned =
        query |> Str.global_replace newline "" |> Str.global_replace tab "";
      "{\"query\": \"" ^ cleaned ^ "\"}"
    };
  };
  module Response = {
    open Yojson.Basic.Util;
    let of_string (response: string) :Yojson.Basic.json =>
      Yojson.Basic.from_string response |> member "data";
  };
};

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

let doit = {
  let headers =
    Header.init_with
      "Authorization" "token c99618a78993f05f62abd1c87f142ec10997d902"
    |> (fun h => Header.add h "User-Agent" "github-pull-requests")
    |> (fun h => Header.add h "Content-Type" "application/json");
  let bodyX = {|
    {
    	user(login:\"mads-hartmann\") {
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
  }
  |};
  let uri = Uri.of_string "https://api.github.com/graphql";
  let req = Cohttp_lwt_body.of_string (GraphQL.Query.of_string bodyX);
  Client.post ::headers body::req uri
  >>= (
    fun (resp, body) => {
      let code = resp |> Response.status |> Code.code_of_status;
      Printf.printf "Response code: %d\n" code;
      Printf.printf
        "Headers: %s\n" (resp |> Response.headers |> Header.to_string);
      body
      |> Cohttp_lwt_body.to_string
      >|= (
        fun body => {
          Printf.printf "Body of length: %d\n" (String.length body);
          body
        }
      )
    }
  )
};

let body = Lwt_main.run doit;

parse (Yojson.Basic.from_string body)
|> List.iter (fun r => print_endline PullRequest.(r.title));
