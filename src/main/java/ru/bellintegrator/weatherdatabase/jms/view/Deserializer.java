package ru.bellintegrator.weatherdatabase.jms.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import ru.bellintegrator.weatherdatabase.forecast.view.ForecastView;
import ru.bellintegrator.weatherdatabase.location.view.LocationView;
import ru.bellintegrator.weatherdatabase.util.Parsing;
import ru.bellintegrator.weatherdatabase.weather.view.WeatherView;
import ru.bellintegrator.weatherdatabase.wind.view.WindView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deserializer extends StdDeserializer<LocationView> {

    private List<WeatherView> listWeather = new ArrayList<>();

    private LocationView location = new LocationView();

    private WindView wind;

    private List<ForecastView> listForecast = new ArrayList<>();

    private JsonNode rootNode;
    private JsonNode weatherNode;
    private JsonNode windNode;
    private JsonNode forecastNode;

    public Deserializer(){
        this(null);
    }

    public Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocationView deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        // Определение узлов
        JsonNode node    = jsonParser.getCodec().readTree(jsonParser);

        // Коренной узел
        rootNode = node.path("payload");

        // Промежуточные узлы
        weatherNode = rootNode.get("weather");
        windNode = weatherNode.path("wind");
        forecastNode = weatherNode.get("forecast");

        // Получить объект Location
        getLocation();

        return location;
    }

    private void getLocation() {
        String city = rootNode.get("city").asText();
        String country = rootNode.get("country").asText();
        String region = rootNode.get("region").asText();

        // Сформировать объект Weather
        getWeather();

        location = new LocationView();
        location.setCity(city);
        location.setCountry(country);
        location.setRegion(region);

        location.setWeather(listWeather);
    }

    private void getWeather() {
        WeatherView weather = new WeatherView();

        int cnt = (Integer) ((IntNode) weatherNode.get("count")).numberValue();
        String created = weatherNode.get("created").asText();
        String lng = weatherNode.get("lang").asText();

        weather.setCount(cnt);
        weather.setCreated(created);
        weather.setLang(lng);

        // Сформировать объект Wind
        getWind();
        weather.setWind(wind);
        // Сформировать listForecast
        getListForecast();
        weather.setForecast(listForecast);

        listWeather.add(weather);
    }

    private void getWind(){
        int chill = (Integer) Integer.parseInt(windNode.get("chill").asText());
        int direction = (Integer) Integer.parseInt(windNode.get("direction").asText());
        int speed = (Integer) Integer.parseInt(windNode.get("speed").asText());

        wind = new WindView();
        wind.setChill(chill);
        wind.setDirection(direction);
        wind.setSpeed(speed);
    }

    private void getListForecast(){
        for (JsonNode node : forecastNode) {
            int code = Integer.parseInt(node.get("code").asText());
            String nodeDate = node.get("date").asText();
            Date outDate = Parsing.parseDate(nodeDate);

            String day = node.get("day").asText();
            int high = Integer.parseInt(node.get("high").asText());
            int low = Integer.parseInt(node.get("low").asText());
            String text = node.get("text").asText();

            ForecastView forecast = new ForecastView();

            forecast.setCode(code);
            forecast.setDate(outDate);
            forecast.setDay(day);
            forecast.setHigh(high);
            forecast.setLow(low);
            forecast.setText(text);

            listForecast.add(forecast);
        }
    }
}