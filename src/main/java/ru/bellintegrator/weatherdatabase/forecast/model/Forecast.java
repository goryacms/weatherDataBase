package ru.bellintegrator.weatherdatabase.forecast.model;

import ru.bellintegrator.weatherdatabase.weather.model.Weather;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "forecast", schema = "weather")
public class Forecast{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "code")
    private Integer code;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "day")
    private String day;

    @Column(name = "high")
    private Integer high;

    @Column(name = "low")
    private Integer low;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id")
    private Weather weather;


    public Forecast() {
    }

    // геттеры/сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
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

    @Override
    public String toString() {
        return "Forecast{" +
                "code=" + getCode() +
                ", date=" + getDate() +
                ", day='" + getDay() + '\'' +
                ", high=" + getHigh() +
                ", low=" + getLow() +
                ", text='" + getText() + '\'' +
                '}';
    }
}
