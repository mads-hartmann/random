/*LOL: How do I put this into another file?!*/
module Hatch = {

    type context = {
        what: string
    };

    type request = {
        httpMethod: string,
        body: string
    };

    type callback = unit => string => unit;

    /* TODO: This is the node specific type. No how we'd like to represent it in reason*/
    type handler = request => context => callback => unit;

    type endpoint =
      | Get string handler
      | Post string handler;

    type service = {
        name: string,
        endpoints: list endpoint
    };

};

let hello = fun (request: Hatch.request) (context: Hatch.context) callback => {
    ignore context; /* Smart way to ignore unused variable. */
    let response = request.body;
    callback () ("How are you?! " ^ response);
};

let example: Hatch.service  = {
    name: "reason-example",
    endpoints: [
        Hatch.Get "/hello" hello
    ]
};
