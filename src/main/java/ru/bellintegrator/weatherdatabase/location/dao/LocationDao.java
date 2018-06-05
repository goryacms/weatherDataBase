package ru.bellintegrator.weatherdatabase.location.dao;

import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;

import java.util.List;

public interface LocationDao {
    void save(Location location);

    List<Location> loadByParams(LocationView locView);

    List<Location> all();
}
