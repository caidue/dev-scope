#!/bin/bash

echo "Cleaning up Docker Compose environment..."

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "ERROR: docker-compose is not installed."
    exit 1
fi

# Navigate to environments directory
cd environments

echo "WARNING: This will completely remove all data including:"
echo "   - PostgreSQL database data"
echo "   - Keycloak configuration and data"
echo "   - All Docker volumes"
echo ""

read -p "Are you sure you want to continue? (y/N): " -n 1 -r
echo

if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "Cleanup cancelled."
    exit 1
fi

echo "Stopping and removing services..."
docker-compose down -v

echo "Removing Docker images..."
docker-compose down --rmi all

echo "Cleaning up unused Docker resources..."
docker system prune -f

echo ""
echo "Cleanup completed successfully!"
echo ""
echo "What was removed:"
echo "   - All Docker containers"
echo "   - All Docker volumes (database data)"
echo "   - All Docker images"
echo "   - Unused Docker resources"
echo ""
echo "To start fresh: ./start-docker.sh" 