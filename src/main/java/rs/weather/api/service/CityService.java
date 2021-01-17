package rs.weather.api.service;

import rs.weather.api.dto.CityDto;

import java.util.List;

public interface CityService {
    /**
     * Method for retrieving full information for all available cities
     *
     * @return List of CityDto's containing full city information
     */
    List<CityDto> getAll();

    /**
     * Method for getting all available city names
     *
     * @return List of available city names
     */
    List<String> getAllNames();

    /**
     * Method for getting all available city names containing passed string (case sensitive)
     *
     * @param partOfName - city name substring
     * @return List of available city names containing passed name part
     */
    List<String> getNamesContaining(String partOfName);

    /**
     * Method that determines whether a city with the passed name is available
     *
     * @param name - name for availability checking
     * @return whether there is a city with the passed name available
     */
    boolean existsByName(String name);
}
