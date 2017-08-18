#!/usr/local/bin/zsh

function set-a {
    a=$1
}

function print-a {
    echo "a = ${a}"
}

function without-local {
    echo "-- without-local"
    print-a
    set-a 1
    print-a
    set-a 2
    print-a
}

function with-local {
    # local -A redefines to be a fresh variable
    echo "-- with-local"
    local a
    print-a
    set-a 42
    print-a
}

with-local
without-local
with-local

# this time a still has the value from previous
# invocation of without-local
without-local
