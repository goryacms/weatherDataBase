package ru.bellintegrator.weatherdatabase.jms.service;


import ru.bellintegrator.weatherdatabase.jms.model.Location;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;


public interface JmsService {
    void saveMessage();

    Location receiveMessage(Location location);
}
