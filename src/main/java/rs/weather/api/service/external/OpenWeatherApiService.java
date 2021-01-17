package rs.weather.api.service.external;

import rs.weather.api.dto.CityDto;

import java.util.List;

public interface OpenWeatherApiService {
    List<CityDto> getCities();

    List<String> getDefaultCities();

    Double getAverageTemperatureForCity(String cityName, Integer daysCount);
}
