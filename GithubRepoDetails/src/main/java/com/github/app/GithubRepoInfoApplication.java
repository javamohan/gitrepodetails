package com.github.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.github")
@EnableJpaRepositories("com.github.repository")
@EntityScan("com.github.entity")
@SpringBootConfiguration
public class GithubRepoInfoApplication {
    private static final Logger log = LoggerFactory.getLogger(GithubRepoInfoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(GithubRepoInfoApplication.class, args);
        log.info("GithubRepoInfoApplication has started...");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
