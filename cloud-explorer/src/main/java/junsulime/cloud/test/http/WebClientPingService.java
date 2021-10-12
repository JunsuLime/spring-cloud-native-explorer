package junsulime.cloud.test.http;

import junsulime.cloud.test.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientPingService implements PingService {

    private final static Logger log = LoggerFactory.getLogger(WebClientPingService.class);

    private final WebClient webClient;

    public WebClientPingService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String ping() {
        String response = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("response: {}", response);

        return response;
    }
}
