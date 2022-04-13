package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Comment findCommentById(UUID uuid);

    List<Comment> findAllByAlbum(Album album);

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByParentN(Comment parentN);

}