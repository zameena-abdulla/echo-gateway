package com.echogateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@PropertySource(value = "classpath:application-test.properties")
public class TestContextConfiguration {
	@Bean(value = "restTemplate")   
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
