#!/bin/bash

if [[ $(docker ps | grep redis) ]]
then
  echo "redis started"
else
  echo "redis not started"
  if [[ $(docker ps -a | grep redis) ]]
  then
    echo "redis image available"
    echo ./restart-redis.bash
    ./restart-redis.bash
  else
    echo "redis image not available"
    echo ./run-redis-image.bash
    ./run-redis-image.bash
  fi
fi
