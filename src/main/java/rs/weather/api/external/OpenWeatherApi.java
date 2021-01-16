package rs.weather.api.external;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
//TODO: Extract this to a separate module/microservice
public final class OpenWeatherApi {
    
    private static final int DEFAULT_COUNT = 10;
    public static final String CITIES_JSON_NAME = "city.list.json";
    private static final String API_KEY = "ffb03fb1d565a5fa4479d381bd66aa6b"; //TODO: Move to YML
    private static final String API_BASE_URL = "api.openweathermap.org/data/2.5/forecast";
    private static final List<String> INITIAL_CITY_NAMES = List.of("London", "Moscow", "Tokyo");

    public static String buildUrl(String city) {
        return buildUrl(null, city);
    }

    public static String buildUrl(Integer count, String city) {
        int cnt = isNull(count) ? DEFAULT_COUNT : count;
        return String.format("%s?appid=%s&cnt=%s&q=%s", API_BASE_URL, API_KEY, cnt, city);
    }
}
