package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.PostUpdate;
import io.tomcode.j4rent.mapper.PostView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public interface IPostService {
    PostView createPost(PostCreate post) throws LatitudeException, LongitudeException, ImageFailException, FloorAreaIncorrectValue, PriceIncorrectValue, UserPostsNotFoundException, PermissionIsNoFound;

    Post getPostById(UUID id);

    Page<PostDetails> getAllPost(Pageable page);

    Page<PostDetails> getAllPost(Pageable page, float floorArea, BigInteger min, BigInteger max);

    Page<PostDetails> getAllPost(Pageable page, float floorArea, BigInteger min, BigInteger max, double latitude, double longitude, double distance);

    Page<PostDetails> getCreatedPosts(Pageable page) throws IdIsNotFoundException;

    Page<PostDetails> getCreatedPosts(Pageable page, float floorArea, BigInteger min, BigInteger max) throws IdIsNotFoundException;

    PostDetails updatePost(PostUpdate post) throws FloorAreaIncorrectValue, PriceIncorrectValue, ImageFailException, UserPostsNotFoundException;

    void deletePost(UUID uuid) throws UserPostsNotFoundException, IdIsNotFoundException, PermissionIsNoFound, IdUserIsNotFoundException;
}
