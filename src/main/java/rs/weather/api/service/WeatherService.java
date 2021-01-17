package rs.weather.api.service;

import rs.weather.api.dto.CityAverageTemperatureDto;

import java.util.List;

public interface WeatherService {
    List<CityAverageTemperatureDto> getCitiesAverageTemperature(List<String> cityNames, Integer daysCount);
}
