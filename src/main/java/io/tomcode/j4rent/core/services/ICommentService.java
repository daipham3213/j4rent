package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.exception.IdNotFoundException;
import io.tomcode.j4rent.mapper.CommentCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ICommentService {

    CommentCreate createComment(CommentCreate commentCreate) throws IdNotFoundException;

    Page<CommentCreate> getComments(Pageable page,UUID uuid) throws IdNotFoundException;

    CommentCreate updateComment (CommentCreate commentCreate) throws IdNotFoundException;
}
