package junsulime.cloud.board.domain;

public class MemoSpecification {

    private final String title;

    public MemoSpecification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
