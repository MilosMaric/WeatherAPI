package rs.weather.api.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import rs.weather.api.dto.CityDto;
import rs.weather.api.dto.TemperatureDto;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
//TODO: Extract to a separate module/microservice
public final class OpenWeatherApi {

    @Value("external-api.default-days_count")
    private static int DEFAULT_COUNT;

    @Value("external-api.cities-resource-file-name")
    public static String CITIES_JSON_NAME;

    @Value("external-api.key")
    private static String API_KEY;

    @Value("external-api.base-url")
    private static String API_BASE_URL;

    @Value("external-api.initial-cities")
    private static String[] INITIAL_CITY_NAMES;

    @Value("external-api.units")
    private static String UNITS;

    private static List<CityDto> cachedCities = List.of();

    private static String buildUrl(String city) {
        return buildUrl(null, city);
    }

    private static String buildUrl(Integer count, String city) {
        int cnt = isNull(count) ? DEFAULT_COUNT : count;
        return String.format("%s?appid=%s&cnt=%s&q=%s&units=%s", API_BASE_URL, API_KEY, cnt, city, UNITS);
    }

    public static List<CityDto> getCities() {
        if (cachedCities.isEmpty()) {
            cachedCities = OpenWeatherApi.loadCitiesFromJSON();
        }

        return cachedCities;
    }

    public static List<String> getDefaultCities() {
        return Arrays.asList(INITIAL_CITY_NAMES);
    }

    public static List<TemperatureDto> getTemperaturesForCity(String name, Integer daysCount) {
        return null;
    }

    private static List<CityDto> loadCitiesFromJSON() {
        List<CityDto> cities = List.of();
        ObjectMapper om = new ObjectMapper();
        ClassLoader classLoader = OpenWeatherApi.class.getClassLoader();
        File jsonFile = new File(Objects.requireNonNull(classLoader.getResource(CITIES_JSON_NAME)).getFile());

        try {
            cities = Arrays.asList(om.readValue(jsonFile, CityDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
