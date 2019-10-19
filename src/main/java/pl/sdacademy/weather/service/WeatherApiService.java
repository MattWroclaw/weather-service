package pl.sdacademy.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sdacademy.weather.dto.WeatherData;
import pl.sdacademy.weather.exception.CityNotFoundException;

@RequiredArgsConstructor
@Component
public class WeatherApiService {

    private final RestTemplate restTemplate;

    @Value("${api.weather.key}")
    private String apiKey;

    @Value("${api.weather.current.url}")
    private String url;

    public WeatherData fetchWeather(String city) {
        String fullUrl = url.replace("{city}", city).replace("{appId}", apiKey);
        try {
            ResponseEntity<WeatherData> response = restTemplate.getForEntity(fullUrl, WeatherData.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CityNotFoundException(city);
            }

            throw e;
        }
    }
}
