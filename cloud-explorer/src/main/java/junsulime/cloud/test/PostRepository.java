package junsulime.cloud.test;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends Repository<Post, UUID> {

    Optional<Post> findById(UUID id);
}
