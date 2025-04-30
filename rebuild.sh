#!/bin/bash

echo "Stopping and removing app container..."
docker-compose -f environments/docker-compose.yml stop app
docker-compose -f environments/docker-compose.yml rm -f app

echo "Removing old Docker image..."
docker rmi dev-scope:latest 2>/dev/null || echo "No old image to remove"

echo "Cleaning up dangling images..."
docker image prune -f

echo "Building new Docker image..."
mvn clean install -DskipTests

echo "Starting app container with new image..."
docker-compose -f environments/docker-compose.yml up -d app

echo "Rebuild completed successfully!"
echo "Your application is now running at http://localhost:8080" 