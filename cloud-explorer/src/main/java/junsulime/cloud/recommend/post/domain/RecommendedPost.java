package junsulime.cloud.recommend.post.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RecommendedPost {

    @Id
    private String id;

    private String title;

    private String content;

    protected RecommendedPost() {
    }

    public RecommendedPost(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
