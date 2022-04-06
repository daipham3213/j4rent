package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.exception.IdNotFound;
import io.tomcode.j4rent.mapper.CommentCreate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ICommentService {

    Comment createComment(CommentCreate commentCreate) throws IdNotFound;

}
