package rs.weather.api.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import rs.weather.api.dto.CityDto;
import rs.weather.api.helpers.HttpHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Component
@ConfigurationProperties(prefix = "external-api")
//TODO: Check why pulling @Value(s) from YML doesn't work
public class OpenWeatherApiServiceImpl implements OpenWeatherApiService {

    //    @Value("${default-days-count}")
    private int DEFAULT_COUNT = 10;

    //    @Value("${cities-resource-file-name}")
    public String CITIES_JSON_NAME = "city.list.json";

    //    @Value("${key}")
    private String API_KEY = "ffb03fb1d565a5fa4479d381bd66aa6b";

    //    @Value("${base-url}")
    private String API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    //    @Value("${initial-cities.city1}")
    private String INITIAL_CITY1 = "London";

    //    @Value("${initial-cities.city2}")
    private String INITIAL_CITY2 = "Moscow";

    //    @Value("${initial-cities.city3}")
    private String INITIAL_CITY3 = "Tokyo";

    //    @Value("${units}")
    private String UNITS = "imperial";

    private List<CityDto> cachedCities = List.of();

    private String buildUrl(Integer count, String city) {
        int cnt = isNull(count) ? DEFAULT_COUNT : count;
        //TODO: Create generic url with params utility
        return String.format("%s?appid=%s&cnt=%s&q=%s&units=%s", API_BASE_URL, API_KEY, cnt, city, UNITS);
    }

    public List<CityDto> getCities() {
        if (cachedCities.isEmpty()) {
            cachedCities = loadCitiesFromJSON();
        }

        return cachedCities;
    }

    public List<String> getDefaultCities() {
        return List.of(INITIAL_CITY1, INITIAL_CITY2, INITIAL_CITY3);
    }

    public Double getAverageTemperatureForCity(String cityName, Integer daysCount) {
        String url = buildUrl(daysCount, cityName);
        String jsonResult = HttpHelper.get(url);
        List<Double> minTemps = extractTemperatures(jsonResult, "temp_min"); //TODO: Move to an enum, avoid 'magic strings'
        List<Double> maxTemps = extractTemperatures(jsonResult, "temp_max"); //TODO: Move to an enum, avoid 'magic strings'
        ArrayList<Double> averageTemps = new ArrayList<>();

        for (int i = 0; i < minTemps.size(); i++) {
            Double minTemp = minTemps.get(i);
            Double maxTemp = maxTemps.get(i);
            averageTemps.add((minTemp + maxTemp) / 2);
        }

        double averageTemperature = Math.round(averageTemps.stream().mapToDouble(d -> d).average().getAsDouble());

        return averageTemperature;
    }

    //TODO: Refactor to JACKSON partial JSON parsing to extract single field/value pairs on random depth
    private List<Double> extractTemperatures(String json, String temperatureName) {
        return Arrays.stream(
                StringUtils.substringsBetween(json, "\"" + temperatureName + "\":", ","))
                .map(Double::parseDouble)
                .collect(toList());
    }

    //TODO: Discuss cities pulling - no API endpoint found for fetching so file/caching was used.
    private List<CityDto> loadCitiesFromJSON() {
        List<CityDto> cities = List.of();
        ObjectMapper om = new ObjectMapper();
        ClassLoader classLoader = OpenWeatherApiServiceImpl.class.getClassLoader();
        File jsonFile = new File(Objects.requireNonNull(classLoader.getResource(CITIES_JSON_NAME)).getFile());

        try {
            cities = Arrays.asList(om.readValue(jsonFile, CityDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
