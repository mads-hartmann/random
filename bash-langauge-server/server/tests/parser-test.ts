import * as P from "parsimmon";

import {Parser} from "../src/parser";
import {Function} from "../src/ast";
import { isFailure } from "../src/parsimon-util";

it('can parse an empty function variant 1', () => {
  const ast = Parser.func.tryParse('function foobar {}');
  expect(ast).toEqual(Function("foobar"))
});

it('can parse an empty function variant 1 with parens', () => {
  const ast = Parser.func.tryParse('function foobar() {}');
  expect(ast).toEqual(Function("foobar"))
});

it('can parse an empty function variant 2', () => {
  const ast = Parser.func.tryParse('foobar () {}');
  expect(ast).toEqual(Function("foobar"))
});

it('gives an error when a function is missing its body', () => {
  const result = Parser.func.parse('function myfunction');

  if (isFailure(result)) {
    expect(result.status).toBe(false);
    expect(result.expected).toEqual(["Expected { after function identifier"]);
  }

});
