#!/usr/bin/env bash
#
# Tiny valid example
#


function foobar() {
  local x=$(which node)
  echo $x
}

echo $(foobar)
