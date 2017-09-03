open Github;

let pullrequests pullrequests => {
  let count = List.length pullrequests;
  print_endline (Printf.sprintf "You have %d pull prequests:" count);
  pullrequests |> List.iter (fun pr => print_endline PullRequest.(pr.title))
};
