package ru.bellintegrator.weatherdatabase.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Weather.class)
public class Weather {
    private int count;

    private String created;

    private String lang;

    private Wind wind;

    private List<Forecast> forecast = new ArrayList<>();


    public Weather() {
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

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "count=" + getCount() +
                ", created='" + getCreated() + '\'' +
                ", lang='" + getLang() + '\'' +
                ", wind=" + getWind() +
                ", forecast=" + getForecast() +
                '}';
    }

}
