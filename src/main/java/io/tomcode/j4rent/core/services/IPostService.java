package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.exception.LatitudeException;
import io.tomcode.j4rent.exception.LongitudeException;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.PostResult;
import liquibase.pro.packaged.P;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;

@Component
public interface IPostService {
    void createPost(PostDetails post) throws LatitudeException, LongitudeException, ImageFailException;

    Page<PostDetails> getAllPost(Pageable page);

    Page<PostDetails> getAllPost(Pageable page, int min, int max);

    Page<PostDetails> getAllPost(Pageable page, int floorArea);

    Page<PostDetails> getCreatedPosts(Pageable page);

    Page<PostDetails> getCreatedPosts(Pageable page, int min, int max);

    Page<PostDetails> getCreatedPosts(Pageable page, int floorArea);
}
