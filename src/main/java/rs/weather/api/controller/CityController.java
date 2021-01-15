package rs.weather.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.weather.api.dto.CityDto;
import rs.weather.api.service.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("city")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public List<CityDto> getAll() {
        return cityService.getAll();
    }
}
 