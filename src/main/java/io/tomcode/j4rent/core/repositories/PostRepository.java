package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}