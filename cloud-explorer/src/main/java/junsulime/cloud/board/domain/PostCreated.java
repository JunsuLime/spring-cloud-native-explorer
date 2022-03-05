package junsulime.cloud.board.domain;

import java.time.Instant;

public class PostCreated extends PostChanged<PostCreated.Data> {

    public static final String EVENT_TYPE = "PostCreated";

    public PostCreated(String id, Data data, Instant occurredAt) {
        super(id, EVENT_TYPE, data, occurredAt);
    }

    public static class Data {
        private final String title;
        private final String content;

        public Data(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
