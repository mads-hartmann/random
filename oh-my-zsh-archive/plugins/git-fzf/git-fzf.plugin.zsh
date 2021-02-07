#
# Utility functions
#

function join-lines {
    local item
    while read item; do
        echo -n "${(q)item} "
    done
}

function is-in-git-repo {
    git rev-parse HEAD > /dev/null 2>&1
}

# convenience function that invokes fzf with a few preset arguments
function fzf-down {
    fzf --height 100% "$@" --border
}

# Takes care of registering a widget and adding key-bindings.
function bind-key-macro {
    local key_strokes=$1
    local func_name=$2
    local widget_name="${func_name}-widget"

    # Define the widget. These are identical for each of the
    # commands so we're generating the function as a string and
    # then eval'ing it.
    eval "
        function ${widget_name} {
            local result=\$(${func_name} | join-lines)
            zle reset-prompt
            LBUFFER+=\$result
        }
    "
    # Register a widget.
    eval "zle -N ${func_name}-widget"
    # Bind the key-stroke to that widget.
    eval "bindkey '${key_strokes}' ${func_name}-widget"
}

#
# Git FZF functions
#

function fzf-git-branch {
    is-in-git-repo || return

    # shows the last ${LINES} commits on the given branch.
    local preview='
        git log --oneline --graph --date=short --pretty="format:%C(auto)%cd %h%d %s" \
        $(sed s/^..// <<< {} | cut -d" " -f1) |
        head -'${LINES}

    git branch -a --color=always |
    grep -v '/HEAD\s' |
    sort |
    fzf-down \
        --ansi \
        --multi \
        --tac \
        --preview-window right:70% \
        --preview ${preview} |
    sed 's/^..//' | cut -d' ' -f1 |
    sed 's#^remotes/##'
}

function fzf-git-status {
    is-in-git-repo || return

    local preview='
        (git diff --color=always -- {-1} | sed 1,4d; cat {-1})
    '

    git -c color.status=always status --short |
    fzf-down \
        -m \
        --ansi \
        --nth 2..,.. \
        --preview ${preview} \
        --bind ctrl-v:preview-page-down,alt-v:preview-page-up|
    cut -c4- |
    sed 's/.* -> //'
}


#
# Key-bindings
#

bind-key-macro '^g^b' 'fzf-git-branch'
bind-key-macro '^g^s' 'fzf-git-status'
