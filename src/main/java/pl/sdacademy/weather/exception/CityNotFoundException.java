package pl.sdacademy.weather.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String city) {
        super("City " + city + " not found!");
    }
}
