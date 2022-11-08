package dev.artjdev.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients(basePackages = "dev.artjdev.proxies")
public class ProjectConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}