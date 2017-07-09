#
# Little script I made to test if signals had some kind of
# hierarchy (e.g. hup also sent another signal).
#
# I doesn't.
#
# ./trap-example.sh
# kill -HUP <pid of ./trap-example.sh>

set -u

sighup() {
    echo "Handling sighup"
    function_that_does_not_exist
}

sigint() {
    echo "Handling sigint"
    exit
}

sigterm() {
    echo "Handling sigterm"
    exit
}

trap "sigterm" TERM
trap "sigint" INT
trap "sighup" HUP

read
