package com.javanexus.spring_security_using_jwt.dtos;


import java.util.List;

public record ForecastItem(long dt,
                           MainWeather main,
                           List<WeatherDescription> weather,
                           Wind wind,
                           String dt_txt) {
}
