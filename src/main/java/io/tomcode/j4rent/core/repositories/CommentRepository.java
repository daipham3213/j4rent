package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}