package junsulime.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CloudApplication implements EnvironmentAware {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment.getProperty("junsulime.intention"));
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
