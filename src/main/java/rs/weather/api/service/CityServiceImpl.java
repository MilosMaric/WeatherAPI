package rs.weather.api.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rs.weather.api.dto.CityDto;
import rs.weather.api.external.OpenWeatherApi;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public List<CityDto> getAll() {
        return OpenWeatherApi.getCities();
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
