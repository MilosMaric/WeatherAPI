package rs.weather.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.weather.api.dto.CityAverageTemperatureDto;
import rs.weather.api.dto.request.AverageTemperatureRequestDto;
import rs.weather.api.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @PostMapping("average")
    public List<CityAverageTemperatureDto> getAverageTemperatureForCities(@RequestBody AverageTemperatureRequestDto dto) {
        return weatherService.getCitiesAverageTemperature(dto.getCityNames(), dto.getDaysCount());
    }
}
