package ru.bellintegrator.weatherdatabase.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bellintegrator.weatherdatabase.jms.service.JmsService;
import java.util.List;

@Controller
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }

    @RequestMapping(value = "/")
    public String welcome(Model model) {
        List<String> cityList = jmsService.getAllCities();

        model.addAttribute("cities",cityList);
        model.addAttribute("title", "Погодный брокер (работа с БД)");
        return "/index";
    }
}