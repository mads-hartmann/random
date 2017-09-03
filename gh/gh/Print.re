open Github;

let error err => {
  print_endline "Oh no \240\159\152\177";
  print_endline (Errors.to_str err)
};

/*
 * Pad the string with spaces until it has the desired length.
 */
let pad str wanted_length =>
  Printf.sprintf
    "%s%s"
    str
    (String.init (wanted_length - String.length str) (fun _ => ' '));

let max xs => List.fold_left (fun max s => s > max ? s : max) 0 xs;

let pullrequests (pullrequests: list PullRequest.t) => {
  open PullRequest;
  let count = List.length pullrequests;
  let tz = Core.Time.Zone.of_string "Europe/Copenhagen";
  let longest_author =
    pullrequests |> List.map (fun pr => String.length pr.author) |> max;
  let longest_title =
    pullrequests |> List.map (fun pr => String.length pr.title) |> max;
  print_endline (Printf.sprintf "\nYou have %d pull prequests:\n" count);
  print_endline (
    Printf.sprintf
      "%s\t%s\t%s\t%s"
      (pad "Last update" 19)
      (pad "Author" longest_author)
      (pad "Title" longest_title)
      "URL"
  );
  pullrequests
  |> List.iter (
       fun pr =>
         print_endline (
           Printf.sprintf
             "%s\t%s\t%s\t%s"
             (Core.Time.format pr.updated_at "%F %T" zone::tz)
             pr.author
             (pad pr.title longest_title)
             pr.url
         )
     );
  print_endline ""
};
