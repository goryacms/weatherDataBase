package ru.bellintegrator.weatherdatabase.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherdatabase.forecast.service.ForecastService;
import ru.bellintegrator.weatherdatabase.forecast.view.ForecastView;
import ru.bellintegrator.weatherdatabase.weather.dao.WeatherDao;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;
import ru.bellintegrator.weatherdatabase.weather.service.WeatherService;
import ru.bellintegrator.weatherdatabase.weather.view.WeatherView;
import ru.bellintegrator.weatherdatabase.wind.service.WindService;
import ru.bellintegrator.weatherdatabase.wind.view.WindView;

import javax.transaction.Transactional;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final WeatherDao dao;
    private final ForecastService forecastService;
    private final WindService windService;

    @Autowired
    public WeatherServiceImpl(WeatherDao dao, ForecastService forecastService, WindService windService) {
        this.dao = dao;
        this.forecastService = forecastService;
        this.windService = windService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(WeatherView weatherView) {
        Weather weather = new Weather();

        weather.setCount(weatherView.getCount());
        weather.setCreated(weatherView.getCreated());
        weather.setLang(weatherView.getLang());
        weather.setLocation(weatherView.getLocation());

        this.dao.save(weather);

        WindView windView = weatherView.getWind();
        windView.setWeather(weather);

        windService.save(windView);

        for (ForecastView forecastView: weatherView.getForecast()) {
            forecastView.setWeather(weather);

            forecastService.save(forecastView);
        }

    }
}
