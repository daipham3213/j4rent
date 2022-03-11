package io.tomcode.j4rent.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableJpaAuditing
@EntityScan(basePackages = {"io.tomcode.j4rent.core.entities"})
@EnableJpaRepositories(basePackages = {"io.tomcode.j4rent.core.repositories"})
@ComponentScan(basePackages = {"io.tomcode.j4rent"})
public class ApplicationConfig {
}
