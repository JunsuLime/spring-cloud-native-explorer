package junsulime.cloud.board.domain;

import junsulime.cloud.event.GenericEvent;

import java.time.Instant;

public abstract class PostChanged<T> extends GenericEvent<T> {

    public static final String STREAM_ID = "post";

    public PostChanged(String id, String type, T data, Instant occurredAt) {
        super(STREAM_ID, id, type, data, occurredAt);
    }
}
