package rs.weather.api.service;

import rs.weather.api.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();
}
