# Nice small convenience key-bindings.
# CTRL+T to select a file in the current directory and paste it.
# CTRL+R to get a nicer backwards history search
[ -f ~/.fzf.zsh ] && source ~/.fzf.zsh

export FZF_DEFAULT_COMMAND='ag -g ""'
export FZF_CTRL_T_COMMAND="$FZF_DEFAULT_COMMAND"
export FZF_CTRL_T_OPTS="--preview '(highlight -O ansi -l {} 2> /dev/null || cat {} || tree -C {}) 2> /dev/null | head -200'"
export FZF_DEFAULT_OPTS="--height 100% --reverse --color=bg+:#000000,hl:#EAC170,hl+:#EAC170,prompt:#EAC170,pointer:#EAC170"
