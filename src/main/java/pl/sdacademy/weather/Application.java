package pl.sdacademy.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("pl.sdacademy.weather")
@PropertySource("application.properties")
public class Application {

    public static void main(String[] args) {
        System.out.println("Hello, Weather!");

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(UserInterface.class).start();
    }
}
