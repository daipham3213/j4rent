package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Post;
import org.springframework.stereotype.Component;

@Component
public interface IPostService {
    void createPost(Post post);
}
