#!/bin/bash

echo "Starting Quarkus Backend in Development Mode"
echo "================================================"

echo ""
echo "Starting Quarkus backend in development mode..."
echo "   - Backend API: http://localhost:8082"
echo "   - OpenAPI Docs: http://localhost:8082/openapi"
echo "   - Health Check: http://localhost:8082/q/health"
echo ""
echo "Development features:"
echo "   - Hot reload enabled"
echo "   - Live coding support"
echo "   - Automatic restart on changes"
echo ""
echo "Press Ctrl+C to stop the server"
echo ""

# Start Quarkus in development mode (dev profile is automatically used)
mvn quarkus:dev -pl backend 