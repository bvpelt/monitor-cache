#!/bin/bash

maxloop=10

if [ $# -eq 1 ]
then
  maxloop=$1
fi

if [[ $maxloop =~ [0-9]{1,3} ]]
then
  echo -n
else
  echo invalid parameter: $maxloop, using default value
  echo "syntax: $0 <counter>"
  maxloop=10
fi

echo maxloop: $maxloop

i=0
while [ $i -lt $maxloop ]
do
  ((i++))
  echo curl http://localhost:8085/customer?id=$i
  curl http://localhost:8085/customer?id=$i
done