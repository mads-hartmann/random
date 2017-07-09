#!/bin/bash

function branch-name {
    git rev-parse --abbrev-ref HEAD
}

function is-on-master {
    if [[ "$(branch-name)" == "master" ]]
    then return 0
    else return 1
    fi
}

function is-on-staging {
    if [[ "$(branch-name)" == "staging" ]]
    then return 0
    else return 1
    fi
}

if is-on-staging || is-on-master
then echo "Deploy"
else echo "Skipping deploy"
fi
