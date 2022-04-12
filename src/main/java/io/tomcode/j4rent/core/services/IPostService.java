package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.PostUpdate;
import io.tomcode.j4rent.mapper.PostView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Component
public interface IPostService {
    PostView createPost(PostCreate post) throws LatitudeException, LongitudeException, ImageFailException, FloorAreaIncorrectValue, PriceIncorrectValue;

    Post getPostById(UUID id);

    Page<PostDetails> getAllPost(Pageable page) throws IdNotFound;

    Page<PostDetails> getAllPost(Pageable page, float floorArea, double min, double max) throws IdNotFound;

    Page<PostDetails> getAllPost(Pageable page, float floorArea, double min, double max, double latitude, double longitude, double distance) throws IdNotFound;

    Page<PostDetails> getCreatedPosts(Pageable page) throws IdNotFound;

    Page<PostDetails> getCreatedPosts(Pageable page, float floorArea, double min, double max) throws IdNotFound;

    PostDetails updatePost(PostUpdate post) throws FloorAreaIncorrectValue, PriceIncorrectValue, ImageFailException, IdNotFound, UserPostsNotFound;
}
