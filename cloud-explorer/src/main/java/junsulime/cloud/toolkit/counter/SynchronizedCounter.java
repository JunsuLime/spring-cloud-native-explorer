package junsulime.cloud.toolkit.counter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Synchronized method
 * 메서드 단위로 동시성이 보장 된다.
 */
public class SynchronizedCounter {

    private static final Logger log = LoggerFactory.getLogger(SynchronizedCounter.class);


    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int value() {
        return count;
    }

    public synchronized void syncMethodSleep(long milliseconds) {
        try {
            log.info("Before syncMethodSleep");
            Thread.sleep(milliseconds);
            log.info("After syncMethodSleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncStatementSleep(long milliseconds) {
        synchronized (this) {
            try {
                log.info("Before syncStatementSleep");
                Thread.sleep(milliseconds);
                log.info("After syncStatementSleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
