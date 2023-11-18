#!/bin/bash

if [[ $(docker ps | grep jaeger) ]]
then
  echo "jaeger started"
else
  echo "jaeger not started"
  if [[ $(docker ps -a | grep jaeger) ]]
  then
    echo "jaeger image available"
    echo ./restart-jaeger.bash
    ./restart-jaeger.bash
  else
    echo "jaeger image not available"
    echo ./run-jaeger.bash
    ./run-jaeger.bash
  fi
fi