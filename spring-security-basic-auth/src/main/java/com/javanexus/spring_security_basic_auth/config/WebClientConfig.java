package com.javanexus.spring_security_basic_auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient weatherWebClient(
            WebClient.Builder webClientBuilder,
            @Value("${weather.api.base-url}") String baseUrl) {

        return webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }
}
