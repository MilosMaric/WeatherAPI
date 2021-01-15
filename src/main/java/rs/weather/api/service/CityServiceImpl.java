package rs.weather.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import rs.weather.api.dto.CityDto;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private List<CityDto> loadCitiesFromJSON() {
        List<CityDto> cities = List.of();
        ObjectMapper om = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(Objects.requireNonNull(classLoader.getResource("city.list.json")).getFile());

        try {
            cities = Arrays.asList(om.readValue(jsonFile, CityDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
