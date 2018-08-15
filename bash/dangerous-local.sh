#!/bin/bash

set -ueo pipefail

function error {
    return 42
}

function my-function {
    local number=$(error)
    local x="what"
    echo "Number is ${number}"
}

my-function
