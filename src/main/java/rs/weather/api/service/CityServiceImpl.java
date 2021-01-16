package rs.weather.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import rs.weather.api.dto.CityDto;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static rs.weather.api.external.OpenWeatherApi.CITIES_JSON_NAME;

@Service
public class CityServiceImpl implements CityService {
    private static List<CityDto> cachedCities = List.of();

    @Override
    public List<CityDto> getAll() {
        if (cachedCities.isEmpty()) {
            cachedCities = loadCitiesFromJSON();
        }

        return cachedCities;
    }

    @Override
    public List<String> getAllNames() {
        return getAll().stream().map(CityDto::getName).collect(toList());
    }

    @Override
    public List<String> getNamesContaining(String partOfName) {
        List<String> names = getAllNames().stream()
                .filter(name -> name.contains(partOfName))
                .collect(toList());

        return names;
    }

    //TODO: Move to API as static method since this is part of the external API
    private List<CityDto> loadCitiesFromJSON() {
        List<CityDto> cities = List.of();
        ObjectMapper om = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(Objects.requireNonNull(classLoader.getResource(CITIES_JSON_NAME)).getFile());

        try {
            cities = Arrays.asList(om.readValue(jsonFile, CityDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
