#!/bin/bash


function branch-name {
    git rev-parse --abbrev-ref HEAD
}

function is-on-master {
    if [[ "$(branch-name)" == "master" ]]
    then true
    else false
    fi
}

function is-on-staging {
    if [[ "$(branch-name)" == "staging" ]]
    then true
    else false
    fi
}

if is-on-staging || is-on-master
then echo "Deploy"
else echo "Skipping deploy"
fi
