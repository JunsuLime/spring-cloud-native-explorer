package junsulime.cloud.board.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends Repository<Post, UUID> {

    Post save(Post post);

    Optional<Post> findById(UUID id);
}
