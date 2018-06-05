package ru.bellintegrator.weatherdatabase.forecast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherdatabase.forecast.dao.ForecastDao;
import ru.bellintegrator.weatherdatabase.forecast.model.Forecast;
import ru.bellintegrator.weatherdatabase.forecast.service.ForecastService;
import ru.bellintegrator.weatherdatabase.forecast.view.ForecastView;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastDao dao;

    @Autowired
    public ForecastServiceImpl(ForecastDao dao) {
        this.dao = dao;
    }


    @Override
    @Transactional
    public void save(ForecastView forecastView) {
        Forecast forecast = new Forecast();

        forecast.setCode(forecastView.getCode());
        forecast.setDate(forecastView.getDate());
        forecast.setDay(forecastView.getDay());
        forecast.setHigh(forecastView.getHigh());
        forecast.setLow(forecastView.getLow());
        forecast.setText(forecastView.getText());
        forecast.setWeather(forecastView.getWeather());

        this.dao.save(forecast);
    }
}
