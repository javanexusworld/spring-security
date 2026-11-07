
package com.javanexus.spring_security_basic_auth.controller;


import com.javanexus.spring_security_basic_auth.dtos.ForecastResponse;
import com.javanexus.spring_security_basic_auth.dtos.WeatherResponse;
import com.javanexus.spring_security_basic_auth.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Get current weather by city
    @GetMapping("/current/{city}")
    public Mono<WeatherResponse> getCurrentWeather(
            @PathVariable
            @NotBlank(message = "City cannot be empty")
            String city) {

      return weatherService.getCurrentWeather(city);
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
    public Mono<WeatherResponse> searchWeather(
            @RequestParam
            @NotBlank(message = "City is required")
            String city) {
         return  weatherService.getCurrentWeather(city);

    }

    @GetMapping("/health")
    public String getHealth(){
        return "Healthy";
    }
}
