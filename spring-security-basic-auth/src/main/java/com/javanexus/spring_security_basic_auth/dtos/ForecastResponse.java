package com.javanexus.spring_security_basic_auth.dtos;

import java.util.List;

public record ForecastResponse(String cod,
                               int message,
                               int cnt,
                               List<ForecastItem> list,
                               City city) {
}
