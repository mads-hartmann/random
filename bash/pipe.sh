# This function can be invoked using an argument. If none is provided it will
# read from stdin.
function pipeable() {
    local input
    input=${1:-$(< /dev/stdin)}
    echo "I was invoked with: ${input}"
}

pipeable "Hello"
echo "world" | pipeable
