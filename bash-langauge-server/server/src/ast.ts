export type Ast = Function | Assignment

export interface Function {
  kind: "function";
  name: string;
}

export function Function(name: string): Function {
  return {
    kind: "function",
    name: name
  }
}

export interface Assignment {
  kind: "assignment";
  name: string
}

export type Program = {
  statements: [Ast]
}
