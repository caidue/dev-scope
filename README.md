# DevScope

A fun interactive app where users select skill cards (e.g., "HTML", "Java", "React", etc.) and receive a personalized summary of what type of developer they are based on their selections.

This is a simple fullstack project that combines Quarkus (Java) backend with Angular frontend. No authentication or database required.

## Project Structure

- `backend`: Quarkus application with REST endpoints
- `docker-image`: Docker image configuration for the backend
- `frontend`: Angular application
- `environments`: Docker Compose configuration for development and production
- `scripts`: Utility scripts for development and deployment

## Prerequisites

- Java 17 or later
- Maven 3.8 or later
- Node.js 20.x
- Docker and Docker Compose
- PostgreSQL 16 (for local development)

## Getting Started

### Port Configuration

| Service | Development Port | Container Port | Purpose |
|---------|------------------|----------------|---------|
| **Container** | - | **8080** | Production-like container |
| **Backend Dev** | **8082** | - | Development server |
| **Frontend Dev Web** | **4200** | - | Angular web dev server |

### Development Mode

#### Option 1: Full Docker Environment
1. Start the development environment:
   ```bash
   ./startup.sh
   ```

This will start:
- **Backend**: Quarkus application at `http://localhost:8080`
- **Frontend**: Angular application embedded in the backend at `http://localhost:8080`

#### Option 2: Local Development (Frontend + Backend)
1. Start the backend in development mode:
   ```bash
   ./dev-backend.sh
   ```

2. Start the frontend in development mode (in a new terminal):
   ```bash
   ./dev-frontend-to-dev-backend.sh
   ```

This will start:
- **Backend**: Quarkus dev mode at `http://localhost:8082`
- **Frontend**: Angular dev server at `http://localhost:4200` (proxying to backend)

### Production Build

To build the complete application as a single Docker image:

```bash
mvn clean install -DskipTests
```
d
This will:
1. Build the Angular frontend (using Yarn)
2. Build the Quarkus backend (with frontend assets embedded)
3. Create a Docker image tagged as `dev-scope:latest`

The Docker image will be available in your local Docker registry and can be used by the Docker Compose configuration.

### Docker Deployment

The application will be available at:
- **Application**: `http://localhost:8080` (Backend API + Frontend)

### Docker Management Scripts

#### Quick Start
```bash
./startup.sh    # Start all services
./shutdown.sh   # Stop all services
```

#### Development Scripts
```bash
./dev-backend.sh                    # Start backend in dev mode (port 8082)
./dev-frontend-to-dev-backend.sh    # Start web frontend in dev mode (port 4200)
```

#### Rebuild Scripts
```bash
./rebuild.sh                        # Rebuild app container
mvn clean install -DskipTests       # Build without Docker
```

#### Manual Docker Compose
```bash
cd environments
docker-compose up -d          # Start services
docker-compose down           # Stop services
docker-compose logs -f        # View logs
docker-compose down -v        # Stop and remove volumes
```

## Features

- **Backend**:
  - Quarkus 3.24.4
  - RESTEasy Reactive
  - OpenAPI documentation
  - Health checks

- **Frontend**:
  - Angular 19.2.14
  - Yarn package management
  - Embedded in backend (single container)
  - Modern UI components

- **Development Tools**:
  - Docker Compose for containerization
  - Development scripts
  - Hot reloading for both frontend and backend

## API Endpoints

- **GET** `/resources/*` - API of the app
- **GET** `/q/health` - Health check endpoint
- **GET** `/openapi` - OpenAPI documentation

## Project Structure

- `backend`: Quarkus application with REST endpoints and embedded frontend
- `frontend`: Angular application (built and embedded in backend)
- `environments`: Docker Compose configuration for development
- `docker-image`: Docker image configuration

## CI/CD Pipeline

This project includes GitHub Actions workflows for continuous integration and deployment:

### Automated Workflows

| Workflow | Trigger | Purpose |
|----------|---------|---------|
| **Pull Request Validation** | PR opened/updated to `main`/`develop` | Validate PR with tests, linting, and quality checks |
| **Release and Manual Build** | Git tags (`v*`) or manual trigger | Create releases with Docker images and full build |

### What Gets Built

- **Frontend**: Angular web application with production build
- **Backend**: Quarkus application with embedded frontend
- **Docker Image**: Complete containerized application
- **Security Scan**: OWASP dependency vulnerability check

### Quality Gates

- Java code formatting (Spotless)
- Code style checks (Checkstyle)
- Frontend linting and type checking
- Unit and integration tests
- Security vulnerability scanning
- Docker image build verification

## Code Quality Tools

This project uses several code quality tools to maintain consistent code style and catch potential issues early.

### Backend Code Quality

#### Spotless (Code Formatting)
Automatically formats Java code according to Google Java Style Guide with 4-space indentation.

```bash
# Check formatting issues
mvn spotless:check

# Apply automatic formatting fixes
mvn spotless:apply
```
a
#### Checkstyle (Code Style)
Validates Java code against style rules and best practices.

```bash
# Check code style
mvn checkstyle:check

# Generate detailed report
mvn checkstyle:checkstyle
```


#### Running All Quality Checks
```bash
# Run all quality checks at once
mvn validate
```

### Frontend Code Quality

For frontend code quality tools (ESLint, Prettier, TypeScript), see the [Frontend README](frontend/README.md#code-quality-tools).