package ru.bellintegrator.weatherdatabase.jms.service;

import org.apache.activemq.command.Message;
import java.util.List;

public interface JmsService {
    void receiveMessage(Message location);

    List<String> getAllCities();
}
