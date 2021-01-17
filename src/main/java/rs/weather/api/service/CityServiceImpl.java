package rs.weather.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rs.weather.api.dto.CityDto;
import rs.weather.api.service.external.OpenWeatherApiService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final OpenWeatherApiService openWeatherApiService;

    @Override
    public List<CityDto> getAll() {
        return openWeatherApiService.getCities();
    }

    @Override
    public List<String> getAllNames() {
        return getAll().stream().map(CityDto::getName).collect(toList());
    }

    @Override
    public List<String> getNamesContaining(String partOfName) {
        return getAllNames().stream().filter(name -> name.contains(partOfName)).collect(toList());
    }

    @Override
    public boolean existsByName(String name) {
        return StringUtils.hasText(name) && getAllNames().contains(name);
    }
}
