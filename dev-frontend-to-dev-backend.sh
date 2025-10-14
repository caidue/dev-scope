#!/bin/bash

echo "Starting Angular Frontend in Development Mode"
echo "================================================"
echo "Connecting to development backend at http://localhost:8082"
echo ""

# Navigate to frontend directory
cd frontend

# Check if node_modules exists, if not install dependencies
if [ ! -d "node_modules" ]; then
    echo "Installing frontend dependencies..."
    yarn install
fi

echo ""
echo "Starting Angular development server..."
echo "   - Frontend: http://localhost:4200"
echo "   - Backend API: http://localhost:8082"
echo ""
echo "Development features:"
echo "   - Hot reload enabled"
echo "   - Live coding support"
echo "   - Automatic restart on changes"
echo "   - Proxy to backend API"
echo ""
echo "Press Ctrl+C to stop the server"
echo ""

# Start Angular development server with proxy to development backend
yarn start:dev 