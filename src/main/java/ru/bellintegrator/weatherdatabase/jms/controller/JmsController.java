package ru.bellintegrator.weatherdatabase.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bellintegrator.weatherdatabase.Application;
import ru.bellintegrator.weatherdatabase.jms.service.JmsService;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;

import java.io.IOException;

@Controller
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }


    @RequestMapping(value = "/")
    public String welcome(Model model) {
        model.addAttribute("title", "Погодный брокер (работа с БД)");
        return "/index";
    }


    @RequestMapping(value = "/jms/save", method = RequestMethod.POST)
    public String weatherSave(Model model) {
        // Сформировать JMS
        jmsService.saveMessage();

        return "redirect:/";
    }


}