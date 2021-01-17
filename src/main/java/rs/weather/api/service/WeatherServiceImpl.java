package rs.weather.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rs.weather.api.dto.CityAverageTemperatureDto;
import rs.weather.api.service.external.OpenWeatherApiService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final CityService cityService;
    private final OpenWeatherApiService openWeatherApiService;

    @Override
    public List<CityAverageTemperatureDto> getCitiesAverageTemperature(List<String> cityNames, Integer daysCount) {
        cityNames = CollectionUtils.isEmpty(cityNames) ? openWeatherApiService.getDefaultCities() : cityNames;

        return cityNames.stream()
                .filter(cityService::existsByName)
                .map(name -> new CityAverageTemperatureDto(name, openWeatherApiService.getAverageTemperatureForCity(name, daysCount)))
                //TODO: Sorting direction wasn't specified, so default (asc) was taken
                .sorted(Comparator.comparingDouble(CityAverageTemperatureDto::getAverageTemperature))
                .collect(Collectors.toList());
    }
}
