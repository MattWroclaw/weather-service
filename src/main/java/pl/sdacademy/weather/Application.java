package pl.sdacademy.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.sdacademy.weather.service.UserInterface;

@Configuration
@ComponentScan("pl.sdacademy.weather")
@PropertySource("application.properties")
public class Application {

    public static void main(String[] args) {
        System.out.println("Hello, Weather!");

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(UserInterface.class).start();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
