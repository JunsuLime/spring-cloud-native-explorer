package junsulime.cloud.recommend.post.domain;

import org.springframework.data.repository.Repository;

public interface RecommendedPostRepository extends Repository<RecommendedPost, String> {

    RecommendedPost save(RecommendedPost recommendedPost);
}
