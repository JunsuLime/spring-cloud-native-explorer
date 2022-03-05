package junsulime.cloud.event;

import java.time.Instant;

public class GenericEvent<T> implements Event<T> {

    private final String streamId;

    private final String id;

    private final String type;

    private final T data;

    private final Instant occurredAt;

    public GenericEvent(String streamId, String id, String type, T data, Instant occurredAt) {
        this.streamId = streamId;
        this.id = id;
        this.type = type;
        this.data = data;
        this.occurredAt = occurredAt;
    }

    @Override
    public String getStreamId() {
        return streamId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public Instant getOccurredAt() {
        return occurredAt;
    }
}
