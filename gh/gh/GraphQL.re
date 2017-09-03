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
