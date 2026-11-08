package com.javanexus.spring_security_using_jwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WaetherWebClientConfig {


    @Bean
    public WebClient.Builder webBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient weatherWebClient(WebClient.Builder webClientBuilder,
                                      @Value("${weather.api.base-url}") String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }
}
