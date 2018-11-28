#!/usr/bin/env bash

VERSION="0.0.1"

HELP="todo - a tiny CLI for managing TODO files

Usage: todo [options] <command>

Commands:

    todo <text>
    todo complete      Completes a specific task
    todo ls|list       Lists your current TODOs
    todo edit          Opens your TODO.md file using your \$EDITOR

Options:

    -v|--version       Prints the current version.
    -h|--help          Prints this message.
"

set -euo pipefail

function main {
    echo ${HELP}
}

main $@
