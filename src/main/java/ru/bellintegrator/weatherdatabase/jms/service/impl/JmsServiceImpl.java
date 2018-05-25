package ru.bellintegrator.weatherdatabase.jms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherdatabase.Application;
import ru.bellintegrator.weatherdatabase.jms.service.JmsService;





import ru.bellintegrator.weatherdatabase.jms.model.Location;




@Service
public class JmsServiceImpl implements JmsService {

    @Value("${inbound.endpoint}")
    private String destination;


    @Override
    public void saveMessage() {

//
//        String msg = null;
//        String msg2 = receiveMessage2(msg);
//
        //System.out.println("saveMessage");


        Location weatherInCity = receiveMessage(new Location());
        System.out.println("Received2<" + weatherInCity.toString() + ">");
        System.out.println("DB = " + weatherInCity.getCity());

    }

//    @JmsListener(destination = "sampleQueue", containerFactory = "jmsFactory")
//    public String receiveMessage2(String msg) {
//        System.out.println("Method Received!!!!!! :" + msg);
//        return msg;
//    }


    @Override
    //@JmsListener(destination = Application.WEATHER_MESSAGE_QUEUE, containerFactory = "jmsFactory")
    @JmsListener(destination = "${inbound.endpoint}", containerFactory = "jmsFactory")
    //public Location receiveMessage(Location location) {
    public Location receiveMessage(Location location) {
        System.out.println("in Method = " + location);
        return location;
    }
}