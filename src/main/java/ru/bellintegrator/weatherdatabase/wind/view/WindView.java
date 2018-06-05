package ru.bellintegrator.weatherdatabase.wind.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherdatabase.weather.model.Weather;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindView {
    private int chill;

    private int direction;

    private int speed;

    private Weather weather;

    public WindView() {
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WindView{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                ", weather=" + weather +
                '}';
    }
}
