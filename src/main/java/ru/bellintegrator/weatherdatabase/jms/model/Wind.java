package ru.bellintegrator.weatherdatabase.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Wind.class)
public class Wind {
    private int chill;

    private int direction;

    private int speed;

    public Wind() {
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

    @Override
    public String toString() {
        return "Wind{" +
                "chill=" + getChill() +
                ", direction=" + getDirection() +
                ", speed=" + getSpeed() +
                '}';
    }
}
