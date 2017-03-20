#!/usr/bin/env bash

echo
echo -------------------------------------------------
echo Setting enviorment
echo -------------------------------------------------

export DOCKER_HOST=unix:///var/run/docker.sock
echo $DOCKER_HOST

echo
echo -------------------------------------------------
echo Stopping and removing existing docker containers
echo -------------------------------------------------

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

echo
echo -------------------------------------------------
echo Building the application
echo -------------------------------------------------

docker run -d --name=mongodb_test -p 27017:27017 bitnami/mongodb:latest
mvn package docker:build
docker stop mongodb_test

echo
echo -------------------------------------------------
echo Starting services
echo -------------------------------------------------

docker-compose up