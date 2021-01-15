package rs.weather.api.external;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpenWeatherApi {
    private static final int DEFAULT_COUNT = 10;
    private static final String API_KEY = "ffb03fb1d565a5fa4479d381bd66aa6b"; //TODO: Move to YML
    private static final String API_BASE_URL = "api.openweathermap.org/data/2.5/forecast";

    public static String buildUrl(String city) {
        return buildUrl(null, city);
    }

    public static String buildUrl(Integer count, String city) {
        int cnt = isNull(count) ? DEFAULT_COUNT : count;
        return String.format("%s?appid=%s&cnt=%s&q=%s", API_BASE_URL, API_KEY, cnt, city);
    }
}
