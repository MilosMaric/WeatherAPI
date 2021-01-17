package rs.weather.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityAverageTemperatureDto {
    private String cityName;
    private double averageTemperature;
}
