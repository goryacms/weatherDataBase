package ru.bellintegrator.weatherdatabase.forecast.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForecastView {

    private int code;

    private Date date;

    private String day;

    private int high;

    private int low;

    private String text;

    private Weather weather;

    public ForecastView() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
