package ru.bellintegrator.weatherdatabase.wind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherdatabase.wind.dao.WindDao;
import ru.bellintegrator.weatherdatabase.wind.model.Wind;
import ru.bellintegrator.weatherdatabase.wind.service.WindService;
import ru.bellintegrator.weatherdatabase.wind.view.WindView;

import javax.transaction.Transactional;

@Service
public class WindServiceImpl implements WindService {
    private final WindDao dao;

    @Autowired
    public WindServiceImpl(WindDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(WindView windView) {
        Wind wind = new Wind();
        wind.setChill(windView.getChill());
        wind.setDirection(windView.getDirection());
        wind.setSpeed(windView.getSpeed());
        wind.setWeather(windView.getWeather());

        this.dao.save(wind);
    }

}
