#!/bin/bash

greenplus='\e[1;33m[+]\e[0m'
yellowstar='\e[1;93m[*]\e[0m'
redexclaim='\e[1;31m[!]\e[0m'

check_for_root() {
    if [ "$EUID" -ne 0 ]
        then echo -e "\n  $redexclaim Script must be run with 'sudo ./${0##*/}' or as root\n"
    exit
    fi
}

cleanup_docker() {
    echo -e "\n  $yellowstar Stopping containers ...\n"
    docker stop $(docker ps -a -q) 2>/dev/null

    echo -e "\n  $yellowstar Removing containers and associated volumes ...\n"
    docker rm -v $(docker ps -a -q) 2>/dev/null

    echo -e "\n  $yellowstar Removing images ...\n"
    docker rmi $(docker images -a -q) 2>/dev/null
}

system_prune() {
    echo -e "\n  $yellowstar Running system prune ...\n"
    docker system prune -f
}

check_for_root
cleanup_docker
system_prune

echo -e "\n  $greenplus All Done! Happy Coding!\n"
