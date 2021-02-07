if [[ -d ${HOME}/.opam ]]; then
  eval `opam config env`
  . ~/.opam/opam-init/init.zsh > /dev/null 2> /dev/null || true
fi
