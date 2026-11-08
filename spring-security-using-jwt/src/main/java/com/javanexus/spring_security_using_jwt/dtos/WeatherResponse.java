package com.javanexus.spring_security_using_jwt.dtos;

public record WeatherResponse(String city,
                              double temperature,
                              double feelsLike,
                              int humidity,
                              String description,
                              double windSpeed) {
}
