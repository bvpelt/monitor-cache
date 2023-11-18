#!/bin/bash
if [[ $(docker ps | grep redis) ]]
then
  echo "redis started"
  echo docker stop $(docker ps -f name=redis -q)
  docker stop $(docker ps -f name=redis -q)
else
  echo "redis not started"
fi