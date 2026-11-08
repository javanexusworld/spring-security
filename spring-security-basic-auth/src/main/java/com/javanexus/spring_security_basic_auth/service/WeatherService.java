package com.javanexus.spring_security_basic_auth.service;

import com.javanexus.spring_security_basic_auth.dtos.ForecastResponse;
import com.javanexus.spring_security_basic_auth.dtos.WeatherResponse;
import com.javanexus.spring_security_basic_auth.exception.WeatherAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;

@Service
public class WeatherService {

    @Autowired
    WebClient weatherWebClient;

    @Value("${weather.api.key}")

    private String apiKey;

    public Mono<WeatherResponse> getCurrentWeather(String city) {
        if (city.contains("Hyderabad")) {
            throw new WeatherAPIException("API is down");
        }
        return weatherWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> new WeatherResponse(
                        response.path("name").asText(),
                        response.path("main").path("temp").asDouble(),
                        response.path("main").path("feels_like").asDouble(),
                        response.path("main").path("humidity").asInt(),
                        response.path("weather").get(0)
                                .path("description").asText(),
                        response.path("wind").path("speed").asDouble()
                ));
    }

    public ForecastResponse getForecast(String city) {
        return weatherWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(ForecastResponse.class)
                .block();
    }
}
