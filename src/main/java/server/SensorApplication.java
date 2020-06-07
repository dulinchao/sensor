package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.service.Main;

@SpringBootApplication
public class SensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorApplication.class, args);
    }

}
