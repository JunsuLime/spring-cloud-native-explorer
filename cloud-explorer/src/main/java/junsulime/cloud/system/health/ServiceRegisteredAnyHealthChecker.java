package junsulime.cloud.system.health;

import org.springframework.web.client.RestTemplate;

public class ServiceRegisteredAnyHealthChecker {

    private final RestTemplate restTemplate;

    public ServiceRegisteredAnyHealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object health() {
        // TODO: serviceId 는 정확히 어떻게 결정되는거지?, spring.application.name 이랑 미묘하게 다른 듯
        return restTemplate.getForObject(String.format("http://cloud-explorer/actuator/health"), Object.class);
    }
}
