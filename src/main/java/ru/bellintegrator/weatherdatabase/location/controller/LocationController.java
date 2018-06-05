package ru.bellintegrator.weatherdatabase.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.weatherdatabase.location.service.LocationService;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;


@RestController
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/weather")
    public LocationView checkCode(@RequestParam(name = "city", required = true) String city) {
        LocationView locView = new LocationView();
        locView.setCity(city);

        LocationView resultView = locationService.loadByParams(locView);

        return resultView;
    }
}