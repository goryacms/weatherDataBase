package ru.bellintegrator.weatherdatabase.jms.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;

@JsonDeserialize(using = Deserializer.class)
public class LocationViewDeserialize extends LocationView {

    public LocationViewDeserialize(){

    }

}
