package com.javanexus.spring_security_basic_auth.dtos;


import java.util.List;

public record ForecastItem(long dt,
                           MainWeather main,
                           List<WeatherDescription> weather,
                           Wind wind,
                           String dt_txt) {
}
