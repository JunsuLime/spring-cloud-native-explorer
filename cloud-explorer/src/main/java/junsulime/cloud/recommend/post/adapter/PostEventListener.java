package junsulime.cloud.recommend.post.adapter;

import junsulime.cloud.board.domain.PostCreated;
import junsulime.cloud.recommend.post.application.RecommendedPostManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PostEventListener {

    private final RecommendedPostManager recommendedPostManager;

    public PostEventListener(RecommendedPostManager recommendedPostManager) {
        this.recommendedPostManager = recommendedPostManager;
    }

    @TransactionalEventListener
    public void on(PostCreated created) {
        recommendedPostManager.createPost(new RecommendedPostManager.CreatePostSpec(
                created.getId(),
                created.getData().getTitle(),
                created.getData().getContent()
        ));
    }
}
