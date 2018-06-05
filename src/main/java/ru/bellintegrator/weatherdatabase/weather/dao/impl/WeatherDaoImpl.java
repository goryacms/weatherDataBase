package ru.bellintegrator.weatherdatabase.weather.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.weatherdatabase.weather.dao.WeatherDao;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;

import javax.persistence.EntityManager;

@Repository
public class WeatherDaoImpl implements WeatherDao {
    private final EntityManager em;

    @Autowired
    public WeatherDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Weather weather) {
        em.persist(weather);

    }
}
