package com.salac.petstore.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConfig {

    private static final Logger log = LoggerFactory.getLogger(FlywayConfig.class);

    @Bean
    public CommandLineRunner runFlywayMigrations(DataSource dataSource, Environment env) {
        return args -> {
            try {
                String locations = env.getProperty("spring.flyway.locations", "classpath:db/migration");
                boolean baselineOnMigrate = Boolean.parseBoolean(env.getProperty("spring.flyway.baseline-on-migrate", "true"));

                log.info("Starting Flyway migrations (locations={}, baselineOnMigrate={})", locations, baselineOnMigrate);

                Flyway flyway = Flyway.configure()
                        .dataSource(dataSource)
                        .locations(locations)
                        .baselineOnMigrate(baselineOnMigrate)
                        .load();

                flyway.migrate();
                log.info("Flyway migrations completed successfully");
            } catch (Exception ex) {
                log.error("Flyway migration failed: {}", ex.getMessage(), ex);
                throw new RuntimeException("Flyway migration failed", ex);
            }
        };
    }
}

