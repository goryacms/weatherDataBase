package ru.bellintegrator.weatherdatabase.location.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherdatabase.weather.view.WeatherView;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationView {

    private String city;

    private String country;

    private String region;

    private List<WeatherView> weather;


    public LocationView() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<WeatherView> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherView> weather) {
        this.weather = weather;
    }


    @Override
    public String toString() {
        return "LocationView{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", weather=" + weather +
                '}';
    }
}
