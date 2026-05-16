package com.salac.petstore;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
@EntityScan(basePackages = "com.salac.petstore.model")
public class PetStoreApplication {

    public static void main(String[] args) {
        runFlywayMigrations();
        SpringApplication.run(PetStoreApplication.class, args);
    }

    private static void runFlywayMigrations() {
        String url = System.getenv().getOrDefault("SPRING_DATASOURCE_URL",
                System.getenv().getOrDefault("DB_URL",
                        String.format("jdbc:postgresql://%s:%s/%s?sslmode=require",
                                System.getenv().getOrDefault("DB_HOST", "localhost"),
                                System.getenv().getOrDefault("DB_PORT", "5432"),
                                System.getenv().getOrDefault("DB_NAME", System.getenv().getOrDefault("POSTGRES_DB", "petstore")))));

        String user = System.getenv().getOrDefault("SPRING_DATASOURCE_USERNAME",
                System.getenv().getOrDefault("DB_USER",
                        System.getenv().getOrDefault("POSTGRES_USER", "petstore")));

        String password = System.getenv().getOrDefault("SPRING_DATASOURCE_PASSWORD",
                System.getenv().getOrDefault("DB_PASSWORD",
                        System.getenv().getOrDefault("POSTGRES_PASSWORD", "password")));

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .validateOnMigrate(false)
                .load();

        flyway.repair();
        flyway.migrate();
    }
}