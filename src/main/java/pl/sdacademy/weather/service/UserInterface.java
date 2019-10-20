package pl.sdacademy.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.sdacademy.weather.dto.WeatherData;
import pl.sdacademy.weather.exception.CityNotFoundException;

import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class UserInterface {

    private final WeatherApiService weatherApiService;
    private final MessageSource messageSource;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose your locale:");
        String localeString = scanner.nextLine();
        Locale locale = new Locale(localeString);

        do {
            String cityName = messageSource.getMessage("city.name", null, locale);
            System.out.print(cityName + ": ");
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
