package junsulime.cloud.system.refresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RefreshCounter {

    private static final Logger log = LoggerFactory.getLogger(RefreshCounter.class);

    private final AtomicLong counter = new AtomicLong();

    public RefreshCounter() {
        log.info("RefreshCounter constructed");
    }

    @EventListener
    public void refresh(RefreshScopeRefreshedEvent e) {
        log.info("count: " + counter.incrementAndGet());
    }
}
