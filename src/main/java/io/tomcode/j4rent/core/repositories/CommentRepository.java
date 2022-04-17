package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends BaseRepository<Comment, UUID> {
    Comment findCommentById(UUID uuid);

    List<Comment> findAllByAlbum(Album album);

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByParentN(Comment parentN);

    @Modifying
    @Query("update Comment u set u.contents =:contents where u.id = :id")
    void updateComment(@Param("id") UUID id, @Param("contents") String contents);

    void deleteAllByParentN(UUID uuid);


}