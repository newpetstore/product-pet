#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin newpetstore-docker-docker.bintray.io

docker push newpetstore-docker-docker.bintray.io/docker/pet:$1
