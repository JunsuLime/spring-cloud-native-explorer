package junsulime.cloud.system.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfHealthCheckController {

    private final SelfHealthChecker selfHealthChecker;

    private final RestTemplateSelfHealthChecker restTemplateSelfHealthChecker;

    private final WebClientSelfHealthChecker webClientSelfHealthChecker;

    public SelfHealthCheckController(SelfHealthChecker selfHealthChecker,
                                     RestTemplateSelfHealthChecker restTemplateSelfHealthChecker,
                                     WebClientSelfHealthChecker webClientSelfHealthChecker) {
        this.selfHealthChecker = selfHealthChecker;
        this.restTemplateSelfHealthChecker = restTemplateSelfHealthChecker;
        this.webClientSelfHealthChecker = webClientSelfHealthChecker;
    }

    @GetMapping("/health")
    public Object health() {
        return selfHealthChecker.amIOk();
    }

    @GetMapping(value = "/health", params = "tool=rest-template")
    public Object healthWithRestTemplate() {
        return restTemplateSelfHealthChecker.health();
    }

    @GetMapping(value = "/health", params = "tool=web-client")
    public Object healthWithWebClient() {
        return webClientSelfHealthChecker.health();
    }


}
