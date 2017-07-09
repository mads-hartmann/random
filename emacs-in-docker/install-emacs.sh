#!/bin/bash

set -e

readonly version="24.5"

# download source package
if [[ ! -d emacs-"$version" ]]; then
   wget http://ftp.gnu.org/gnu/emacs/emacs-"$version".tar.xz
   tar xvf emacs-"$version".tar.xz
fi

# buil and install
mkdir -p /usr/local/stow
cd emacs-"$version"
./configure \
    --with-xft \
    --with-x-toolkit=lucid
make
make install prefix=/usr/local/stow/emacs-"$version"
cd /usr/local/stow
stow emacs-"$version"
