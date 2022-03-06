package junsulime.cloud.system.health;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RestTemplateSelfHealthCheckerTest.Config.class)
class RestTemplateSelfHealthCheckerTest {

    @Autowired
    private RestTemplateSelfHealthChecker sut;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void healthTest() {
        server.expect(requestTo("/actuator/health")).andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
        Object response = sut.health();

        assertNotNull(response);
    }

    @Configuration
    public static class Config {

        @Bean
        public RestTemplateSelfHealthChecker restTemplateSelfHealthChecker(RestTemplateBuilder restTemplateBuilder) {
            return new RestTemplateSelfHealthChecker(restTemplateBuilder.build());
        }
    }
}