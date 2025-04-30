# Frontend Module

This module contains the frontend application for DevScope with shared code.

## Structure

```
frontend/
├── shared/           # Shared Angular library
├── web/             # Web Angular application
└── package.json     # Root package.json with workspace configuration
```

## Shared Library

The `shared/` directory contains an Angular library with common code that can be used by the web application:

- **Services**: API services, authentication, etc.
- **Models**: TypeScript interfaces and types
- **Components**: Reusable UI components
- **Guards**: Route guards for authentication
- **Interceptors**: HTTP interceptors

## Web Application

The `web/` directory contains the traditional Angular web application that runs in browsers.


## Development

### Prerequisites

- Node.js v20.11.1
- Yarn v1.22.22
- Angular CLI

### Setup

1. Install dependencies for all modules:

    ```bash
    yarn install:all
    ```

2. Build the shared library:
    ```bash
    yarn build:shared
    ```

### Running Development Servers

#### Web Application

```bash
yarn start:web
```

The web app will be available at `http://localhost:4200`


### Building for Production

Build all applications:

```bash
yarn build
```

Build individual applications:

```bash
yarn build:shared
yarn build:web
```

#
#### Building for Mobile

```bash
# Android
yarn ionic:build:android

# iOS
yarn ionic:build:ios
```

### Testing

Run tests for all modules:

```bash
yarn test
```

Run tests for individual modules:

```bash
yarn test:shared
yarn test:web
```

### Code Quality Tools

#### Linting (ESLint)

Validates TypeScript/JavaScript code against style rules and best practices.

```bash
# Check for linting issues
yarn lint

# Fix linting issues automatically
yarn lint:fix

# Run linting for specific modules
yarn lint:shared
yarn lint:web
```

#### Code Formatting (Prettier)

Automatically formats TypeScript, JavaScript, HTML, CSS, and JSON files.

```bash
# Format all code
yarn format

# Format specific modules
yarn format:shared
yarn format:web
```

#### Type Checking

Validates TypeScript code for type errors.

```bash
# Run type checking
yarn type-check

# Build with type checking
yarn build
```

#### Running All Quality Checks

```bash
# Run linting, formatting, and type checking
yarn lint && yarn format && yarn type-check
```

## Adding New Shared Code

1. Add your code to the appropriate directory in `shared/src/lib/`
2. Export it from the corresponding `index.ts` file
3. Export it from `shared/src/public-api.ts`
4. Build the shared library: `yarn build:shared`
5. Import and use it in your web application

## Project Scripts

The root `package.json` provides convenient scripts for managing all modules:

- `install:all` - Install dependencies for all modules
- `build:shared` - Build the shared library
- `build:web` - Build the web application
- `build` - Build all applications
- `start:web` - Start web development server
- `test` - Run tests for all modules
- `lint` - Run linting for all modules
- `format` - Format code in all modules
