#!/bin/bash

# Navigate to the environments directory
cd "$(dirname "$0")/environments"

# Build and start all services
docker-compose up --build -d