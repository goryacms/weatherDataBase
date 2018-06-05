package ru.bellintegrator.weatherdatabase.weather.model;

import ru.bellintegrator.weatherdatabase.forecast.model.Forecast;
import ru.bellintegrator.weatherdatabase.location.model.Location;
import ru.bellintegrator.weatherdatabase.wind.model.Wind;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weather", schema = "weather")
public class Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "count")
    private Integer count;

    @Column(name = "created")
    private String created;

    @Column(name = "lang")
    private String lang;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(
            mappedBy = "weather",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Wind wind;

    @OneToMany(
            mappedBy = "weather",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Forecast> forecasts;


    public Weather() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }


    @Override
    public String toString() {
        return "Weather{" +
                "count=" + getCount() +
                ", created='" + getCreated() + '\'' +
                ", lang='" + getLang() + '\'' +
                ", wind=" + getWind() +
                ", forecast=" + getForecasts() +
                '}';
    }
}
