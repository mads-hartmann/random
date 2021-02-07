function switch-project {
    clear
    paths=($(find ~/dev/* -maxdepth 0 -type d)) # all paths
    names=${paths##~/dev/} # remove longest match of ~/dev/ from paths
    selection=$(echo ${names:gs/ /\\n/} | fzf --reverse) # substitution adds newlines

    if [ $status -eq 0 ]; then
        clear
        cd ~/dev/$selection
    else
        echo "No valid made"
    fi
}

# Ctrl+u to delete line, project, enter.
bindkey -s '^[p' '^Uswitch-project^M'
