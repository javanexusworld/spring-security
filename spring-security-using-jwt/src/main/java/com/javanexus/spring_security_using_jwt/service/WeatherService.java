package com.javanexus.spring_security_using_jwt.service;


import com.javanexus.spring_security_using_jwt.dtos.ForecastResponse;
import com.javanexus.spring_security_using_jwt.dtos.WeatherResponse;
import com.javanexus.spring_security_using_jwt.entities.WeatherReport;
import com.javanexus.spring_security_using_jwt.exception.WeatherAPIException;
import com.javanexus.spring_security_using_jwt.repos.WeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    WebClient weatherWebClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private WeatherReportRepository repository;

    public WeatherResponse getCurrentWeather(String city) {
        return weatherWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .exchangeToMono(clientResponse -> {

                    System.out.println("========== WEATHER API ==========");
                    System.out.println("Status: " + clientResponse.statusCode());

                    if (clientResponse.statusCode().isError()) {
                        return clientResponse.bodyToMono(String.class)
                                .defaultIfEmpty("Empty error response")
                                .flatMap(error -> {
                                    System.out.println("Error body: " + error);

                                    return Mono.error(
                                            new WeatherAPIException(
                                                    "External API error: " + error));
                                });
                    }

                    return clientResponse.bodyToMono(JsonNode.class);
                })
                .map(response -> new WeatherResponse(
                        response.path("name").asText(),
                        response.path("main").path("temp").asDouble(),
                        response.path("main").path("feels_like").asDouble(),
                        response.path("main").path("humidity").asInt(),
                        response.path("weather").get(0)
                                .path("description").asText(),
                        response.path("wind").path("speed").asDouble()
                ))
                .doOnSuccess(response ->
                        System.out.println("Weather response mapped successfully"))
                .doOnError(error ->
                        System.out.println("Service exception: " + error))
                .block();
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

    // CREATE
    public WeatherReport createWeatherReport(WeatherReport report) {
        return repository.save(report);
    }

    // GET ALL
    public List<WeatherReport> getAllWeatherReports() {
        return repository.findAll();
    }

    // GET BY ID
    public WeatherReport getWeatherReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new WeatherAPIException(
                                "Weather report not found with ID: " + id));
    }

    public List<WeatherReport> getWeatherReportsByCity(String city) {
        return repository.findByCityIgnoreCaseOrderByFetchedAtDesc(city);
    }


    public WeatherReport updateWeatherReport(
            Long id, WeatherReport updatedReport) {

        WeatherReport existingReport = getWeatherReportById(id);

        existingReport.setCity(updatedReport.getCity());
        existingReport.setTemperature(updatedReport.getTemperature());
        existingReport.setFeelsLike(updatedReport.getFeelsLike());
        existingReport.setHumidity(updatedReport.getHumidity());
        existingReport.setDescription(updatedReport.getDescription());
        existingReport.setWindSpeed(updatedReport.getWindSpeed());

        return repository.save(existingReport);
    }

    public void deleteWeatherReport(Long id) {
        WeatherReport existingReport = getWeatherReportById(id);
        repository.delete(existingReport);
    }
}
