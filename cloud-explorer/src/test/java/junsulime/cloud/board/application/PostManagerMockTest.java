package junsulime.cloud.board.application;

import junsulime.cloud.board.domain.Post;
import junsulime.cloud.board.domain.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostManagerMockTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private PostManager sut;

    @Test
    void write() {
        // Given
        final PostManager.WritePostSpec writePostSpec = new PostManager.WritePostSpec("title", "content");
        given(postRepository.save(any())).willAnswer((Answer<Post>) invocation -> invocation.getArgument(0));

        // When
        sut.writePost(writePostSpec);

        // Then
        ArgumentCaptor<Post> argumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(argumentCaptor.capture());

        final Post post = argumentCaptor.getValue();
        assertEquals(post.getTitle(), "title");
        assertEquals(post.getContent(), "content");
    }
}