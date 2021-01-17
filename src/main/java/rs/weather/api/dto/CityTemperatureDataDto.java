package rs.weather.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CityTemperatureDataDto {
    List<TemperatureDto> main;
}
