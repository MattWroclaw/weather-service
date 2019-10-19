package pl.sdacademy.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sdacademy.weather.dto.WeatherData;

@Component
public class WeatherApiService {

    private RestTemplate restTemplate;

    @Value("${api.weather.key}")
    private String apiKey;

    @Value("${api.weather.current.url}")
    private String url;

    public WeatherApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData fetchWeather(String city) {
        String fullUrl = url.replace("{city}", city).replace("{appId}", apiKey);
        return restTemplate.getForObject(fullUrl, WeatherData.class);
    }
}
