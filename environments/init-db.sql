-- Create databases for the application
CREATE DATABASE appdb;
CREATE DATABASE keycloak;

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE appdb TO postgres;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO postgres; 