package junsulime.cloud.test.http;

import junsulime.cloud.test.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplatePingService implements PingService {

    private static final Logger log = LoggerFactory.getLogger(RestTemplatePingService.class);

    private final RestTemplate restTemplate;

    public RestTemplatePingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String ping() {
        String response = restTemplate.getForObject("/", String.class);
        log.info("response: {}", response);

        return response;
    }
}
