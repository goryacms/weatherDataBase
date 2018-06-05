package ru.bellintegrator.weatherdatabase.forecast.dao;

import ru.bellintegrator.weatherdatabase.forecast.model.Forecast;

public interface ForecastDao {
    void save(Forecast forecast);
}
