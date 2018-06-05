package ru.bellintegrator.weatherdatabase.weather.dao;

import ru.bellintegrator.weatherdatabase.weather.model.Weather;

public interface WeatherDao {
    void save(Weather weather);
}
