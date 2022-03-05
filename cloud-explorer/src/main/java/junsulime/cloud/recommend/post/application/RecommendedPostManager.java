package junsulime.cloud.recommend.post.application;

import junsulime.cloud.recommend.post.domain.RecommendedPost;
import junsulime.cloud.recommend.post.domain.RecommendedPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendedPostManager {

    private final RecommendedPostRepository recommendedPostRepository;

    public RecommendedPostManager(RecommendedPostRepository recommendedPostRepository) {
        this.recommendedPostRepository = recommendedPostRepository;
    }

    public void createPost(CreatePostSpec spec) {
        recommendedPostRepository.save(new RecommendedPost(spec.getId(), spec.getTitle(), spec.getContent()));
    }

    public static class CreatePostSpec {

        private String id;

        private String title;

        private String content;

        public CreatePostSpec(String id, String title, String content) {
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
}
