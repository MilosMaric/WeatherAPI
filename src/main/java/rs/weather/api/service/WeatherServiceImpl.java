package rs.weather.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.weather.api.dto.CityAverageTemperatureDto;
import rs.weather.api.external.OpenWeatherApi;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final CityService cityService;

    @Override
    public List<CityAverageTemperatureDto> getCitiesAverageTemperature(List<String> cityNames, Integer daysCount) {
        return cityNames.stream()
                .filter(cityService::existsByName)
                .map(name -> new CityAverageTemperatureDto(name, calculateAverageTemperature(name, daysCount)))
                .collect(Collectors.toList());
    }

    private double calculateAverageTemperature(String name, Integer daysCount) {
        double averageTemperature = OpenWeatherApi.getTemperaturesForCity(name, daysCount).stream()
                .mapToDouble(dto -> (dto.getTemp_max() + dto.getTemp_min()) / 2)
                .average()
                .getAsDouble();

        return averageTemperature;
    }
}
