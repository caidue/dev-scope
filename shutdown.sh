#!/bin/bash

# Navigate to the environments directory
cd "$(dirname "$0")/environments"

# Stop the services
docker-compose down