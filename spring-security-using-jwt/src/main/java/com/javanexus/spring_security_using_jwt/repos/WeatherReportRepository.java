package com.javanexus.spring_security_using_jwt.repos;

import com.javanexus.spring_security_using_jwt.entities.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport,Long> {
    List<WeatherReport> findByCityIgnoreCaseOrderByFetchedAtDesc(String city);
}
