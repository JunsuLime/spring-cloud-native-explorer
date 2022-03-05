package junsulime.cloud.system.health;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(HealthConfiguration.class)
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
}