if [[ -n "$ITERM_SESSION_ID" ]]; then

  function change-tab-color() {
    echo -ne "\033]6;1;bg;red;brightness;$1\a"
    echo -ne "\033]6;1;bg;green;brightness;$2\a"
    echo -ne "\033]6;1;bg;blue;brightness;$3\a"
  }

  function change-profile() {
    echo -ne "\033]50;SetProfile=$1\a"
  }

  function precmd-hook () {
    # Note: If this turns out to be too slow I can cache the context for a bit.
    if [[ $(command -v kubectl) ]]; then
      context=$(kubectl config current-context)
      if [[ "$context" =~ "production" ]]; then
        change-profile Danger
        change-tab-color 255 0 0
      else
        echo -ne "\033]6;1;bg;*;${ITERM_THEME:-Default}\a"
        change-profile ${ITERM_THEME:-Default}
      fi
    fi
  }

  autoload -U add-zsh-hook
  add-zsh-hook precmd precmd-hook
fi
