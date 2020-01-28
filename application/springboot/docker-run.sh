#!/bin/bash
docker run -p 8080:8080 \
       -i --rm \
       -e MONGODB_URI=mongodb://kkk:88/teste \
       pet:$1
