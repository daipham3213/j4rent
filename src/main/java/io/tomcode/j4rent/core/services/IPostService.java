package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.exception.LatitudeException;
import io.tomcode.j4rent.exception.LongitudeException;
import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Component
public interface IPostService {
    void createPost(PostCreate post) throws LatitudeException, LongitudeException, ImageFailException;

    Post getPostById(UUID id);

    Page<PostDetails> getAllPost(Pageable page);


    Page<PostDetails> getAllPost(Pageable page, int floorArea , int min ,int max);

    Page<PostDetails> getCreatedPosts(Pageable page);


    Page<PostDetails> getCreatedPosts(Pageable page, int floorArea, int min, int max);
}
