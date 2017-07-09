# Emacs in Docker

Run an Emacs daemon server inside of Docker.

    docker run -d -p 2620:2620 -v $PWD:/workdir:rw -v ~/.emacs.d/server:/emacs-server:rw mhj/emacs emacs

    emacsclient --eval '(setq server-auth-dir "/workdir/emacs-server")' \
                --server-file=indocker .


TODO: Seems that when I mount the god damn directory it won't actually write the damn file!!!

Ideas:

- Use volumes from to debug/edit in-docker files.
