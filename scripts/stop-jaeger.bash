#!/bin/bash
if [[ $(docker ps | grep jaeger) ]]
then
  echo "jaeger started"
  echo docker stop $(docker ps -f name=jaeger -q)
  docker stop $(docker ps -f name=jaeger -q)
else
  echo "jaeger not started"
fi