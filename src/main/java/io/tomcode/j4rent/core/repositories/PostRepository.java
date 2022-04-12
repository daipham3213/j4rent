package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
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


    String HAVERSINE_PART = "(3956 * 2 * ASIN(SQRT( POWER(SIN((:latitude - abs(p.latitude)) * pi()/180 / 2), 2) + COS(:longitude * pi()/180 ) * COS(abs(p.latitude) * pi()/180)  * POWER(SIN((:longitude - p.longitude) * pi()/180 / 2), 2) )))";
    @Query(value = "select distinct p from Post p where p.price between :min and :max and p.floorArea between 0 and :floorArea and :distance >= " + HAVERSINE_PART+" ")
    List<Post> findPostsByCoordinates(@Param("distance") double distance, @Param("latitude") double latitude, @Param("longitude") double longitude, @Param("floorArea") float floorArea, @Param("min") BigInteger min, @Param("max") BigInteger max);
}