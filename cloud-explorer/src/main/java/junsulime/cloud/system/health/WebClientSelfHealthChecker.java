package junsulime.cloud.system.health;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientSelfHealthChecker {

    private final WebClient webClient;

    public WebClientSelfHealthChecker(WebClient webClient) {
        this.webClient = webClient;
    }

    public Object health() {
        return webClient.get()
                .uri("/actuator/health")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
