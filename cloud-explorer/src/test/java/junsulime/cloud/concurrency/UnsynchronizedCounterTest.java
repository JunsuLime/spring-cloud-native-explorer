package junsulime.cloud.concurrency;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("장비 환경에 따라 결과가 다르게 나올 수 있다.")
class UnsynchronizedCounterTest {

    private static final Logger log = LoggerFactory.getLogger(UnsynchronizedCounterTest.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Test
    void synchronizedCount() throws InterruptedException {
        final UnsynchronizedCounter counter = new UnsynchronizedCounter();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(counter::increment);
        }
        for (int i = 0; i < 10000; i++) {
            executorService.execute(counter::decrement);
        }

        executorService.shutdown();
        final boolean terminated = executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertTrue(terminated);
        log.info("count: {}", counter.value());
        assertNotEquals(counter.value(), 0);
    }
}