package com.javanexus.spring_security_using_jwt.entities;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_reports",schema = "security")
public class WeatherReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private Double temperature;

    private Double feelsLike;

    private Integer humidity;

    private String description;

    private Double windSpeed;

    private LocalDateTime fetchedAt;

    @PrePersist
    public void prePersist() {
        if (fetchedAt == null) {
            fetchedAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }
}
