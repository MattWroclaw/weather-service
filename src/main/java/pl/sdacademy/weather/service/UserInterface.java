package pl.sdacademy.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sdacademy.weather.dto.WeatherData;
import pl.sdacademy.weather.exception.CityNotFoundException;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class UserInterface {

    private final WeatherApiService weatherApiService;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("City name: ");
            String city = scanner.nextLine();
            System.out.println("Fetching weather data for city " + city);
            try {
                WeatherData weather = weatherApiService.fetchWeather(city);
                System.out.println("Current temperature in " + city + ": " + weather.getMain().getTemp() + "\u00B0C");
            } catch (CityNotFoundException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Search again? (Y/N)");
            String again = scanner.nextLine();
            if ("N".equalsIgnoreCase(again)) return;
        } while (true);
    }
}
