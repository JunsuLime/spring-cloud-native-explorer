package junsulime.cloud.toolkit.counter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SynchronizedCounterTest {

    private static final Logger log = LoggerFactory.getLogger(SynchronizedCounterTest.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Test
    void synchronizedCount() throws InterruptedException {
        final SynchronizedCounter counter = new SynchronizedCounter();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(counter::increment);
        }
        for (int i = 0; i < 10000; i++) {
            executorService.execute(counter::decrement);
        }

        executorService.shutdown();
        final boolean terminated = executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertTrue(terminated);
        assertEquals(counter.value(), 0);
    }

    @Test
    void syncMethodTest() throws InterruptedException {
        final SynchronizedCounter counter = new SynchronizedCounter();
        executorService.execute(() -> counter.syncMethodSleep(1000));

        Thread.sleep(500);
        log.info("After sleep 500");

        // increment is synchronized, so wait syncSleep
        counter.increment();
        log.info("After increment");
    }

    @Test
    void syncStatementTest() throws InterruptedException {
        final SynchronizedCounter counter = new SynchronizedCounter();
        executorService.execute(() -> counter.syncStatementSleep(1000));

        Thread.sleep(500);
        log.info("After sleep 500");

        // increment is synchronized, so wait syncStatementSleep
        counter.increment();
        log.info("After increment");
    }
}