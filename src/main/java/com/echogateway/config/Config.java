package com.echogateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.echogateway")
public class Config {
	@Bean(value = "restTemplate")   
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
