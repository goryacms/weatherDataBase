package ru.bellintegrator.weatherdatabase.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Location.class)
public class Location implements Serializable {
    private static final long serialVersionUID = -2749977649306134186L;

    private String city;

    private String country;

    private String region;

    private Weather weather;

    public Location() {
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

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }


    @Override
    public String toString() {
        return "Location{" +
                "city='" + getCity() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", region='" + getRegion() + '\'' +
                ", weather=" + getWeather() +
                '}';
    }
}