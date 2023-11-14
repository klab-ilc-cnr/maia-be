package it.cnr.ilc.projectx;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;
import static java.util.TimeZone.getTimeZone;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(getTimeZone("UTC"));
    }

}
