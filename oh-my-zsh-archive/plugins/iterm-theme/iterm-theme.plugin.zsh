function iterm-theme() {
    echo -e "\033]50;SetProfile=$1\a"

    # This is to make sure that my danger-danger plugin doesn't immediately
    # switch the profile back to 'Default' on each pre-command hook.
    export ITERM_THEME=$1
}
