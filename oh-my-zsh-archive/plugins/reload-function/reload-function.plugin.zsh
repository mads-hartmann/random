function reload-function {
    local function_name=$1
    unfunction ${function_name}
    autoload -U ${function_name}
}
