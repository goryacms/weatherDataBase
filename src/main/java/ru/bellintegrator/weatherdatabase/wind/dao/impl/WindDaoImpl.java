package ru.bellintegrator.weatherdatabase.wind.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.weatherdatabase.wind.dao.WindDao;
import ru.bellintegrator.weatherdatabase.wind.model.Wind;

import javax.persistence.EntityManager;


@Repository
public class WindDaoImpl implements WindDao {
    private final EntityManager em;

    @Autowired
    public WindDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Wind wind) {
        em.persist(wind);
    }
}
