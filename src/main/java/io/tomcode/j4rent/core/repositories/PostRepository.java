package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByCreatedByIdEquals(UUID createdById);

    List<Post> findByCreatedByIdEquals(UUID createdById, Pageable pageable);

    List<Post> findByCreatedByIdEqualsAndFloorAreaLessThanEqualAndPriceBetween(UUID createdById, float floorArea, double priceStart, double priceEnd, Pageable pageable);

    List<Post> findByFloorAreaLessThanEqualAndPriceBetween(float floorArea, double priceStart, double priceEnd, Pageable pageable);

    Post findPostById(UUID id);

    @Query(value = "SELECT DISTINCT COUNT (c.id) FROM  Comment c , Post p where c.post=p AND p =:post")
    Long countCommentInPost(@Param("post") Post post);

    @Query(value = "SELECT DISTINCT c.id FROM  Comment c , Post p where c.post.id=p.id AND p =:post")
    List<UUID> findCommentInPost(@Param("post") Post post);

    @Query(value = "SELECT distinct id from Comment  where parentN.id=:id")
    List<UUID> findComment(@Param("id") UUID uuid);

}