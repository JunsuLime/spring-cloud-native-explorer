package junsulime.cloud;

import junsulime.cloud.config.ConfigurationProjectProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CloudApplication implements EnvironmentAware {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment.getProperty("junsulime.intention"));
    }
}
