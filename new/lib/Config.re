type t = {
  version: int,
  templates_path: string
};

let from_json json => {
  open Yojson.Basic.Util;

  let templates = json
    |> member "templates"
    |> to_string;

  Some {
    version: 1,
    templates_path: templates
  }
};

let read path => {
  Yojson.Basic.from_file path
  |> from_json
}
