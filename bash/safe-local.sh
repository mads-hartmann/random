#!/bin/bash

set -ueo pipefail

function error {
    return 42
}

function my-function {
    local number
    number=$(error)
    echo "Number is ${number}"
}

my-function
