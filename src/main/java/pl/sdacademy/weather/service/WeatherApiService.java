package pl.sdacademy.weather.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.sdacademy.weather.dto.WeatherData;

import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class WeatherApiService {

    private Gson gson;
    private ResourceLoader resourceLoader;

    @Value("${api.weather.key}")
    private String apiKey;

    @Value("${api.weather.current.url}")
    private String url;

    public WeatherApiService(Gson gson, ResourceLoader resourceLoader) {
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }

    public WeatherData fetchWeather(String city) {
        String fullUrl = url.replace("{city}", city).replace("{appId}", apiKey);
        try (
                InputStream weatherJson = resourceLoader.getResource(fullUrl).getInputStream();
                InputStreamReader reader = new InputStreamReader(weatherJson)
        ) {
            return gson.fromJson(reader, WeatherData.class);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong when downloading weather data", e);
        }
    }
}
