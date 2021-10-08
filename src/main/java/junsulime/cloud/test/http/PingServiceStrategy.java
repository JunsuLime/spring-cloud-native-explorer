package junsulime.cloud.test.http;

import junsulime.cloud.test.PingService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Random;

@Primary
@Service
public class PingServiceStrategy implements PingService {

    private final RestTemplatePingService restTemplatePingService;

    private final WebClientPingService webClientPingService;

    private final Random random = new Random();

    public PingServiceStrategy(RestTemplatePingService restTemplatePingService, WebClientPingService webClientPingService) {
        this.restTemplatePingService = restTemplatePingService;
        this.webClientPingService = webClientPingService;
    }

    @Override
    public String ping() {
        if (random.nextBoolean()) {
            return restTemplatePingService.ping();
        } else {
            return webClientPingService.ping();
        }
    }
}
