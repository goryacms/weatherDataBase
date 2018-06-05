package ru.bellintegrator.weatherdatabase.location.service;


import ru.bellintegrator.weatherdatabase.location.view.LocationView;


public interface LocationService {
    void save(LocationView locationView);

    LocationView loadByParams(LocationView locView);

}
