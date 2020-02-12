#!/bin/bash
gradle properties \
       --no-daemon \
       --console=plain -q \
       | grep "^version:" \
       | awk '{printf $2}'