package com.jgeekmz.management_app.services;

import com.jgeekmz.management_app.models.CurrentWeather;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StubWeatherService {
    public CurrentWeather getCurrentWeather(String city, String country) {
        return new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO);
    }
}