package ru.bellintegrator.weatherdatabase.forecast.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.weatherdatabase.forecast.dao.ForecastDao;
import ru.bellintegrator.weatherdatabase.forecast.model.Forecast;
import javax.persistence.EntityManager;

@Repository
public class ForecastDaoImpl implements ForecastDao {
    private final EntityManager em;

    @Autowired
    public ForecastDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Forecast forecast) {
        em.persist(forecast);

    }
}
