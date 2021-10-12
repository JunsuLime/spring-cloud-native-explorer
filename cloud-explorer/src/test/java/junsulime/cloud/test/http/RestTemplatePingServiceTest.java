package junsulime.cloud.test.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RestTemplatePingService.class)
class RestTemplatePingServiceTest {

    @Autowired
    private RestTemplatePingService restTemplatePingService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void pingTest() throws Exception {
        this.server.expect(requestTo("/")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        String response = this.restTemplatePingService.ping();

        assertEquals(response, "hello");
    }

}