package junsulime.cloud.event;

import java.time.Instant;

public interface Event<T> {

    String getStreamId();

    String getId();

    String getType();

    T getData();

    Instant getOccurredAt();

}
