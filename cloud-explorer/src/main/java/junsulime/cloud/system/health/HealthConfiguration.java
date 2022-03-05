package junsulime.cloud.system.health;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HealthConfiguration {

    @Bean
    public RestTemplateSelfHealthChecker restTemplateSelfHealthChecker(RestTemplateBuilder restTemplateBuilder) {
        return new RestTemplateSelfHealthChecker(restTemplateBuilder.build());
    }

    @Bean
    public WebClientSelfHealthChecker webClientSelfHealthChecker(WebClient.Builder webClientBuilder) {
        return new WebClientSelfHealthChecker(webClientBuilder.build());
    }
}
