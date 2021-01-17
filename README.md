# WeatherAPI

WeatherAPI is a wrapper API around [OpenWeatherAPI](https://openweathermap.org/forecast5) which provides detailed
weather data for any city around the world.

## Usage

After running the project as a regular spring boot application (through an IDE or by building/running WAR through a
console), the REST API will be available on port 5000.

## API Calls

| URL | Method |  Description | 
| --- | ------------ | ----------- | 
| /city | GET | Getter for full data objects of all available cities | 
| /city/names | GET | Getter for city names of all available cities | 
| /city/names/{namePart} | GET | Getter for city names of available cities that contain passed string | 
| /weather/average | POST | Method that calculates average temperatures for passed number of days for each passed city name separately. If no cities are passed, default ones are taken. If no days count is passed, default of 10 is passed. If any of the passed city names doesn't exist, that city is omitted from the result. | 