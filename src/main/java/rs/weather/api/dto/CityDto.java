package rs.weather.api.dto;

import lombok.Data;

@Data
public class CityDto {
    private long id;
    private String name;
    private String state;
    private String country;
    private Coordinates coord;
}
