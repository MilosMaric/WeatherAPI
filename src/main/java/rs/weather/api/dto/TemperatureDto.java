package rs.weather.api.dto;

import lombok.Data;

@Data
public class TemperatureDto {
    private double temp_min;
    private double temp_max;
}
