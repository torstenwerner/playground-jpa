package de.wps.playground;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.wps.playground")
public class ApplicationConfig {
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/db/migration/V1__initial.sql")
                .build();
    }
}
