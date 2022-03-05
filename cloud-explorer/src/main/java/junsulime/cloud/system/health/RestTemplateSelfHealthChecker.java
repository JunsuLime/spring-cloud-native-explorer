package junsulime.cloud.system.health;

import org.springframework.web.client.RestTemplate;

public class RestTemplateSelfHealthChecker {

    private final RestTemplate restTemplate;

    public RestTemplateSelfHealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object health() {
        return restTemplate.getForObject("/actuator/health", Object.class);
    }
}
