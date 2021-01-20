package rs.weather.api.service;

import rs.weather.api.dto.CityAverageTemperatureDto;

import java.util.List;

public interface WeatherService {
    /**
     * Method that calculates average temperatures for each passed city and passed days period
     *
     * @param cityNames - city names for average temperature calculation
     * @param daysCount - number of days for average temperature calculation
     * @return List of objects where each contains a city name and its respective average temperature for given period.
     */
    List<CityAverageTemperatureDto> getCitiesAverageTemperature(List<String> cityNames, Integer daysCount);
    
    List<CityAverageTemperatureDto> getCitiesAverageTemperature();
}
