package junsulime.cloud.config;

import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RefreshCounter {

    private final AtomicLong counter = new AtomicLong();

    public RefreshCounter() {
        System.out.println("RefreshCounter constructed");
    }

    @EventListener
    public void refresh(RefreshScopeRefreshedEvent e) {
        System.out.println("count: " + counter.incrementAndGet());
    }
}
