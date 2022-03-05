package junsulime.cloud.board.application;

import junsulime.cloud.board.domain.Post;
import junsulime.cloud.board.domain.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PostManagerSpringTest {

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private PostManager sut;

    @BeforeEach
    void setUp() {
        sut = new PostManager(postRepository, applicationEventPublisher);
    }

    @Test
    void writePost() {
        given(postRepository.save(any())).willAnswer((Answer<Post>) invocation -> {
            final Post saved = invocation.getArgument(0);
            ReflectionTestUtils.setField(saved, "id", UUID.randomUUID());
            return saved;
        });

        sut.writePost(new PostManager.WritePostSpec("title", "content"));
    }
}
