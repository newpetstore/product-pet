#!/bin/bash
HERE=$(dirname $0)

docker build $HERE -t $DOCKER_REGISTRY/pet:$1
