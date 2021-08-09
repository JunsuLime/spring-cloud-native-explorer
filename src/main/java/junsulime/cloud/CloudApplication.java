package junsulime.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class);
    }

    public CloudApplication(ConfigurationProjectProperties properties) {
        System.out.println("constructor: " + properties);
    }

}
