package ru.bellintegrator.weatherdatabase.location.model;

import ru.bellintegrator.weatherdatabase.weather.model.Weather;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "location", schema = "weather")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Weather> weathers;


    public Location() {
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

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", version=" + version +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", weathers=" + weathers +
                '}';
    }
}
