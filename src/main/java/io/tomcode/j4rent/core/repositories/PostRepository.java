package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends BaseRepository<Post, UUID> {
    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *" +
            " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";
    List<Post> findByCreatedByIdEquals(UUID createdById);

    List<Post> findByCreatedByIdEquals(UUID createdById, Pageable pageable);

    List<Post> findByCreatedByIdEqualsAndFloorAreaLessThanEqualAndPriceBetween(UUID createdById, float floorArea, BigInteger price, BigInteger price2, Pageable page);

    List<Post> findByFloorAreaLessThanEqualAndPriceBetween(float floorArea, BigInteger priceStart, BigInteger priceEnd, Pageable pageable);

    Post findPostById(UUID id);

    @Query(value = "SELECT DISTINCT COUNT (c.id) FROM  Comment c , Post p where c.post=p AND p =:post")
    Long countCommentInPost(@Param("post") Post post);

    @Query(value = "SELECT DISTINCT c.id FROM  Comment c , Post p where c.post.id=p.id AND p =:post")
    List<UUID> findCommentInPost(@Param("post") Post post);

    @Query(value = "SELECT distinct id from Comment  where parentN.id=:id")
    List<UUID> findComment(@Param("id") UUID uuid);

    @Query(value = "SELECT s FROM Post s WHERE " + HAVERSINE_FORMULA + " <= :distance and (s.price between :min and :max) and (s.floorArea between 0 and :floorArea) ORDER BY "+ HAVERSINE_FORMULA + " DESC")
    List<Post> findPostsByCoordinates(@Param("distance") double distance, @Param("latitude") double latitude, @Param("longitude") double longitude, @Param("floorArea") float floorArea, @Param("min") BigInteger min, @Param("max") BigInteger max, Pageable pageable);
}