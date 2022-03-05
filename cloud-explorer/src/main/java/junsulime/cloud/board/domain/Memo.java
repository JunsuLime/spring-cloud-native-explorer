package junsulime.cloud.board.domain;

import java.util.Objects;

public class Memo {

    private long id;

    private String title;

    private String content;

    public Memo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Memo(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memo memo = (Memo) o;
        return id == memo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
