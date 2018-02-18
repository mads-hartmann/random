import { TextDocument, Definition, Diagnostic } from "vscode-languageserver/lib/main";

import {isSuccess, isFailure} from './parsimon-util';
import {Program} from './ast'
import {Parser} from "./parser";

type FileMap = {
  [uri: string]: Program
}

const files: FileMap = {}

export function analyze(document: TextDocument): Diagnostic[] {
  const result = Parser.program.parse(document.getText());
  if (isFailure(result)) {
    // TODO: Return the actual diagnostics.
  } else if (isSuccess(result)) {
    files[document.uri] = result.value
  }
  return [];
}

export function findDefinition(uri: string, line: number, char: number): Definition {
  // TODO
  return null;
}
