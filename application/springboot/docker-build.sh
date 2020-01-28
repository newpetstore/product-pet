#!/bin/bash
HERE=$(dirname $0)

docker build $HERE -t newpetstore-docker-docker.bintray.io/docker/pet:$1
