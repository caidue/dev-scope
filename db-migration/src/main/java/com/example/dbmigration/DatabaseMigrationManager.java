package com.caidu.dbmigration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.jboss.logging.Logger;

public class DatabaseMigrationManager {
    private static final Logger LOG = Logger.getLogger(DatabaseMigrationManager.class);
    private static final String FLYWAY_TABLE = "schema_version";
    private static final String FLYWAY_LOCATION = "classpath:db/migration";

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DatabaseMigrationManager(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public void migrate() {
        final Flyway flyway = new Flyway(getDefaultConfig());
        beforeMigration(flyway);
        flyway.migrate();
        afterMigration(flyway);
    }

    public void validate() {
        final Flyway flyway = new Flyway(getDefaultConfig());
        flyway.validate();
    }

    private FluentConfiguration getDefaultConfig() {
        return Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .baselineOnMigrate(true)
                .table(FLYWAY_TABLE)
                .locations(FLYWAY_LOCATION);
    }

    private void beforeMigration(Flyway flyway) {
        final MigrationInfo migrationInfo = flyway.info().current();
        if (migrationInfo != null) {
            LOG.infof("Found database at version: %s - Last migration: %s",
                    migrationInfo.getVersion(),
                    migrationInfo.getDescription());
        } else {
            LOG.warn("No existing database found at the current datasource");
        }
    }

    private void afterMigration(Flyway flyway) {
        final MigrationInfo migrationInfo = flyway.info().current();
        if (migrationInfo != null) {
            LOG.infof("Successfully migrated to version: %s - Last migration: %s",
                    migrationInfo.getVersion(),
                    migrationInfo.getDescription());
        } else {
            LOG.warn("No database version information available after migration");
        }
    }
}