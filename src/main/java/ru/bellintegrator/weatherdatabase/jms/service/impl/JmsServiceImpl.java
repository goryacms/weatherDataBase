package ru.bellintegrator.weatherdatabase.jms.service.impl;

import javax.jms.JMSException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherdatabase.jms.view.LocationViewDeserialize;
import ru.bellintegrator.weatherdatabase.jms.service.JmsService;
import ru.bellintegrator.weatherdatabase.location.dao.LocationDao;
import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.location.service.LocationService;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JmsServiceImpl implements JmsService {
    private final LocationService locationService;
    private final LocationDao locationDao;


    @Value("${inbound.endpoint}")
    private String destination;

    @Autowired
    public JmsServiceImpl(LocationService locationService, LocationDao locationDao) {
        this.locationService = locationService;
        this.locationDao = locationDao;
    }

    @Override
    @Transactional
    @JmsListener(destination = "${inbound.endpoint}", containerFactory = "jmsFactory")
    public void receiveMessage(Message message) {
        String mes = null;
        try {
            mes = ((ActiveMQTextMessage) message).getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            LocationView location = objMapper.readerFor(LocationViewDeserialize.class).readValue(mes);
            locationService.save(location);
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }

    @Override
    @Transactional
    public List<String> getAllCities() {
        List<Location> all = locationDao.all();

        List<String> cityList = new ArrayList<>();

        all.forEach(location -> {
            cityList.add(location.getCity());
        });
        return cityList;
    }
}