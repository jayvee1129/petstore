package com.salac.petstore.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    private static final Logger log = LoggerFactory.getLogger(FlywayConfig.class);

    @Bean
    public FlywayMigrationStrategy repairAndMigrateStrategy() {
        return flyway -> {
            try {
                log.info("Repairing Flyway schema history before migration");
                flyway.repair();
                flyway.migrate();
                log.info("Flyway migrations completed successfully");
            } catch (Exception ex) {
                log.error("Flyway migration failed: {}", ex.getMessage(), ex);
                throw ex;
            }
        };
    }
}

