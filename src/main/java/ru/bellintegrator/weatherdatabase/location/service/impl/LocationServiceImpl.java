package ru.bellintegrator.weatherdatabase.location.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherdatabase.forecast.model.Forecast;
import ru.bellintegrator.weatherdatabase.forecast.view.ForecastView;
import ru.bellintegrator.weatherdatabase.location.dao.LocationDao;
import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.location.service.LocationService;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;
import ru.bellintegrator.weatherdatabase.weather.service.WeatherService;
import ru.bellintegrator.weatherdatabase.weather.view.WeatherView;
import ru.bellintegrator.weatherdatabase.wind.view.WindView;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationDao dao;
    private final WeatherService weatherService;

    @Autowired
    public LocationServiceImpl(LocationDao dao, WeatherService weatherService) {
        this.dao = dao;
        this.weatherService = weatherService;
    }

    @Override
    @Transactional
    public void save(LocationView locView) {
        // Если Location уже есть в базе, то берём из бд
        List<Location> locList = this.dao.loadByParams(locView);

        Location location = null;

        if(locList.size() > 0) {
            location = locList.get(0);
            System.out.println("after loadByParams = " + location);
        }

        // Если нет в бд, то формируем новый Location и сохраняем в бд
        if(location == null) {
            location = new Location();
            location.setCity(locView.getCity());
            location.setCountry(locView.getCountry());
            location.setRegion(locView.getRegion());

            this.dao.save(location);
        }


        for (WeatherView weatherView: locView.getWeather()) {
            weatherView.setLocation(location);

            weatherService.save(weatherView);
        }

    }

    @Override
    @Transactional
    public LocationView loadByParams(LocationView locView){
        Location location = this.dao.loadByParams(locView).get(0);

        LocationView view = new LocationView();
        view.setCity(location.getCity());
        view.setCountry(location.getCountry());
        view.setRegion(location.getRegion());

        Function<Forecast, ForecastView> mapForecast = p2 -> {
            ForecastView forecastView = new ForecastView();
            forecastView.setCode(p2.getCode());
            forecastView.setDate(p2.getDate());
            forecastView.setDay(p2.getDay());
            forecastView.setHigh(p2.getHigh());
            forecastView.setLow(p2.getLow());
            forecastView.setText(p2.getText());

            return forecastView;
        };

        Function<Weather, WeatherView> mapWeather = p -> {
            WeatherView weatherView= new WeatherView();
            weatherView.setCount(p.getCount());
            weatherView.setCreated(p.getCreated());
            weatherView.setLang(p.getLang());

            WindView windView = new WindView();
            windView.setChill(p.getWind().getChill());
            windView.setDirection(p.getWind().getDirection());
            windView.setSpeed(p.getWind().getSpeed());

            weatherView.setWind(windView);

            List<ForecastView> forecastList = p.getForecasts().stream()
                    .map(mapForecast)
                    .collect(Collectors.toList());

            weatherView.setForecast(forecastList);

            return weatherView;
        };

        List<WeatherView> weatherViewList = location.getWeathers().stream()
                .map(mapWeather)
                .collect(Collectors.toList());

        view.setWeather(weatherViewList);

        return view;
    }
}