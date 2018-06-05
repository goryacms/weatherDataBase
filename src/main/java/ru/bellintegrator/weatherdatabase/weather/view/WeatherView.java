package ru.bellintegrator.weatherdatabase.weather.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherdatabase.forecast.view.ForecastView;
import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.wind.view.WindView;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherView {
    private int count;

    private String created;

    private String lang;

    private WindView wind;

    private List<ForecastView> forecast = new ArrayList<>();

    private Location location;

    public WeatherView() {
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public WindView getWind() {
        return wind;
    }

    public void setWind(WindView wind) {
        this.wind = wind;
    }

    public List<ForecastView> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastView> forecast) {
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "WeatherView{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", wind=" + wind +
                ", forecast=" + forecast +
                ", location=" + location +
                '}';
    }
}
