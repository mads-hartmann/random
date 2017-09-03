open Github;

let error err => {
  print_endline "Oh no \240\159\152\177";
  print_endline (Errors.to_str err)
};

let pullrequests pullrequests => {
  let count = List.length pullrequests;
  print_endline (Printf.sprintf "You have %d pull prequests:" count);
  pullrequests |> List.iter (fun pr => print_endline PullRequest.(pr.title))
};
