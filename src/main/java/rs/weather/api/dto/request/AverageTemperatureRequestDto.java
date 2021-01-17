package rs.weather.api.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AverageTemperatureRequestDto {
    private List<String> cityNames;
    private Integer daysCount;
}
