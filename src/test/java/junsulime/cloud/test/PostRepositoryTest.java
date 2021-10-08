package junsulime.cloud.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void id를_기반으로_게시글을_찾는다() {
        UUID id = UUID.randomUUID();
        Post saved = entityManager.persist(new Post(id, "title", "content"));

        Post post = postRepository.findById(id).orElseThrow(NoSuchElementException::new);
        assertEquals(post.getId(), saved.getId());
        assertEquals(post.getTitle(), saved.getTitle());
        assertEquals(post.getContent(), saved.getContent());
    }
}