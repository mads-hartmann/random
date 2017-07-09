#!/bin/bash
set -e

case "$1" in

        emacs-gui)
            emacs
            ;;

        emacs)
            emacs --no-window-system \
                  --eval '(setq server-port 2620)' \
                  --eval '(setq server-host "localhost")' \
                  --eval '(setq server-auth-dir "~/.emacs.d/server")' \
                  --eval "(setq server-use-tcp t)" \
                  --daemon=indocker \
            && tail -f /dev/null # keep the container alive.
            ;;

        *)
            echo $"Unknown option, defaulting to bash '$1'"
            /bin/bash
esac
