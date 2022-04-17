package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.exception.CommentIsNotFoundException;
import io.tomcode.j4rent.exception.IdIsNotFoundException;
import io.tomcode.j4rent.exception.IdUserIsNotFoundException;
import io.tomcode.j4rent.mapper.CommentCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ICommentService {

    CommentCreate createComment(CommentCreate commentCreate) throws IdIsNotFoundException;

    Page<CommentCreate> getComments(Pageable page,UUID uuid) throws IdIsNotFoundException;

    CommentCreate updateComment (CommentCreate commentCreate) throws IdIsNotFoundException, CommentIsNotFoundException, IdUserIsNotFoundException;

    void deleteComment(UUID uuid) throws IdUserIsNotFoundException, CommentIsNotFoundException;
}
