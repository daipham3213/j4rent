package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.exception.IdNotFound;
import io.tomcode.j4rent.mapper.CommentCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ICommentService {

    CommentCreate createComment(CommentCreate commentCreate) throws IdNotFound;

    Page<CommentCreate> getComments(Pageable page,UUID uuid) throws IdNotFound;

    Long countComment (Post post) throws IdNotFound;

}
