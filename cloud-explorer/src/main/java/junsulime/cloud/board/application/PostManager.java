package junsulime.cloud.board.application;

import junsulime.cloud.board.domain.Post;
import junsulime.cloud.board.domain.PostCreated;
import junsulime.cloud.board.domain.PostRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class PostManager {

    private final PostRepository postRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PostManager(PostRepository postRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.postRepository = postRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void writePost(WritePostSpec spec) {
        final Post saved = postRepository.save(new Post(spec.getTitle(), spec.getContent()));
        applicationEventPublisher.publishEvent(new PostCreated(
                saved.getId().toString(),
                new PostCreated.Data(saved.getTitle(), saved.getContent()),
                Instant.now()));
    }

    public static class WritePostSpec {
        private final String title;
        private final String content;

        public WritePostSpec(String title, String content) {
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
