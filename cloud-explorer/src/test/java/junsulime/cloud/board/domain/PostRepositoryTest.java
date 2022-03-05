package junsulime.cloud.board.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void id를_기반으로_게시글을_찾는다() {
        // Given
        Post saved = entityManager.persist(new Post("title", "content"));
        final UUID id = saved.getId();

        // When
        Post sut = postRepository.findById(id).orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(sut.getId(), saved.getId());
        assertEquals(sut.getTitle(), saved.getTitle());
        assertEquals(sut.getContent(), saved.getContent());
    }
}