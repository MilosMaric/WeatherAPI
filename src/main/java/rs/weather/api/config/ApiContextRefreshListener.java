package rs.weather.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rs.weather.api.service.WeatherService;

@Component
@RequiredArgsConstructor
public class ApiContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    private final WeatherService weatherService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        weatherService.getCitiesAverageTemperature();
    }
}
