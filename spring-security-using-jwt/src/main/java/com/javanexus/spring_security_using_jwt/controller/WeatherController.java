package com.javanexus.spring_security_using_jwt.controller;


import com.javanexus.spring_security_using_jwt.dtos.ForecastResponse;
import com.javanexus.spring_security_using_jwt.dtos.WeatherResponse;
import com.javanexus.spring_security_using_jwt.entities.WeatherReport;
import com.javanexus.spring_security_using_jwt.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Get current weather by city
    @GetMapping("/current/{city}")
    public ResponseEntity<WeatherResponse> getCurrentWeather(
            @PathVariable
            @NotBlank(message = "City cannot be empty")
            String city) {

        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }

    // Get weather forecast by city
    @GetMapping("/forecast/{city}")
    public ResponseEntity<ForecastResponse> getForecast(
            @PathVariable
            @NotBlank(message = "City cannot be empty")
            String city) {

        ForecastResponse response =
                weatherService.getForecast(city);

        return ResponseEntity.ok(response);
    }

    // Search weather by city
    @GetMapping("/search")
    public ResponseEntity<WeatherResponse> searchWeather(
            @RequestParam
            @NotBlank(message = "City is required")
            String city) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));

    }

    // CREATE
    @PostMapping
    public ResponseEntity<WeatherReport> createWeatherReport(
            @RequestBody WeatherReport report) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(weatherService.createWeatherReport(report));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<WeatherReport>> getAllWeatherReports() {

        return ResponseEntity.ok(
                weatherService.getAllWeatherReports());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<WeatherReport> getWeatherReportById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                weatherService.getWeatherReportById(id));
    }

    // GET BY CITY
    @GetMapping("/city/{city}")
    public ResponseEntity<List<WeatherReport>> getWeatherReportsByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                weatherService.getWeatherReportsByCity(city));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<WeatherReport> updateWeatherReport(
            @PathVariable Long id,
            @RequestBody WeatherReport report) {

        return ResponseEntity.ok(
                weatherService.updateWeatherReport(id, report));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherReport(
            @PathVariable Long id) {

        weatherService.deleteWeatherReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public String getHealth() {
        return "Healthy";
    }
}
