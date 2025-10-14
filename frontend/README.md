# Frontend Module

This module contains the frontend application for DevScope.

## Structure

```
frontend/
├── web/             # Web Angular application
└── package.json     # Root package.json with workspace configuration
```

## Web Application

The `web/` directory contains the traditional Angular web application that runs in browsers.

## Development

### Prerequisites

- Node.js v20.11.1
- Yarn v1.22.22
- Angular CLI

### Setup

1. Install dependencies:

    ```bash
    yarn install
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
yarn build:web
```

## Testing

Run tests for all modules:

```bash
yarn test
```

Run tests for individual modules:

```bash
yarn test:web
```

## Code Quality Tools

### Linting

Run linting for all modules:

```bash
yarn lint
```

Run linting for individual modules:

```bash
yarn lint:web
```

Fix linting issues:

```bash
yarn lint:fix
yarn lint:fix:web
```

### Formatting

Format all code:

```bash
yarn format
```

Format individual modules:

```bash
yarn format:web
```

Check formatting without making changes:

```bash
yarn format:check
```

## Project Scripts

- `start:web` - Start web development server
- `build:web` - Build the web application
- `build` - Build all applications
- `test` - Run tests for all modules
- `lint` - Run linting for all modules
- `format` - Format all code
- `clean` - Remove dist and node_modules directories

## Development Workflow

1. Make changes to your code
2. Run tests: `yarn test`
3. Check linting: `yarn lint`
4. Format code: `yarn format`
5. Build application: `yarn build`