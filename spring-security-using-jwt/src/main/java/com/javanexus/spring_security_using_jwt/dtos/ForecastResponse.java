package com.javanexus.spring_security_using_jwt.dtos;


import java.util.List;

public record ForecastResponse(String cod,
                               int message,
                               int cnt,
                               List<ForecastItem> list,
                               City city) {
}
