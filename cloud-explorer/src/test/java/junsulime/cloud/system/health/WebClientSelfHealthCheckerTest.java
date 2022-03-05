package junsulime.cloud.system.health;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.function.Consumer;

class WebClientSelfHealthCheckerTest {

    private static final Logger log = LoggerFactory.getLogger(WebClientSelfHealthCheckerTest.class);

    private MockWebServer server;

    private WebClientSelfHealthChecker webClientSelfHealthChecker;

    @BeforeEach
    void setUp() {
        this.server = new MockWebServer();
        WebClient webClient = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector())
                .baseUrl(this.server.url("").toString())
                .build();
        this.webClientSelfHealthChecker = new WebClientSelfHealthChecker(webClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void pingTest() {
        prepareResponse(response -> response.setResponseCode(HttpStatus.OK.value()));

        Object response = webClientSelfHealthChecker.health();
        log.info("response: {}", response);

        expectRequestCount(1);
        expectRequest(request -> Assertions.assertEquals(request.getPath(), "/actuator/health"));
    }

    private void prepareResponse(Consumer<MockResponse> consumer) {
        MockResponse response = new MockResponse();
        consumer.accept(response);
        this.server.enqueue(response);
    }

    private void expectRequest(Consumer<RecordedRequest> consumer) {
        try {
            consumer.accept(this.server.takeRequest());
        } catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void expectRequestCount(int count) {
        Assertions.assertEquals(this.server.getRequestCount(), count);
    }

}