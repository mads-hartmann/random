import * as P from "parsimmon";
import * as Ast from "./ast"

const whitespace = P.regexp(/\s*/m);

function token(parser: any): P.Parser<string> {
  return parser.skip(whitespace);
}

function word(str: string): P.Parser<string> {
  return P.string(str).thru(token);
}

type Bash = {
  lbrace: string,
  rbrace: string,
  identifier: string,
  func: Ast.Function,
  program: Ast.Program
};

export const Parser = P.createLanguage<Bash>({
  // tokens
  lbrace: () => word("{"),
  rbrace: () => word("}"),
  // literals
  identifier: () => P.regexp(/[a-zA-Z0-9\-]+/).skip(whitespace),
  // constructs
  func: (r: P.TypedLanguage<Bash>) => {
    return word('function')
      .then(r.identifier)
      .map((n) => Ast.Function(n))
      .skip(whitespace)
      .skip(r.lbrace).desc('Expected { after function identifier')
      .skip(r.rbrace)
  },
  // program
  program: (r: P.TypedLanguage<Bash>) => {
    return P.seq<Ast.Ast>(r.func)
      .map((xs: [Ast.Ast]) => {
        const p: Ast.Program = {
          statements: xs
        }
        return p
      });
  }
});
