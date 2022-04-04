package io.tomcode.j4rent.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.PostRepository;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.PostDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service("postService")
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final DocumentService documentService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final IAlbumService albumService;

    public PostService(PostRepository postRepository, DocumentService documentService, ModelMapper modelMapper, ObjectMapper objectMapper, AlbumService albumService) {
        this.postRepository = postRepository;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.albumService = albumService;
    }

    @Override
    public void createPost(PostDetails postDetails) throws ImageFailException {
        Document document = documentService.createDocument("post", postDetails);
        documentService.createDocument(document);
        Album album = albumService.createAlbum(postDetails.getAlbum());
        Post post = new Post(postDetails);
        post.setAlbum(album);
        postRepository.save(post);
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page) {
        return getPostDetails(page);
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, int mix, int max) {
        return getPostDetails(page, mix, max);
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, int floorArea) {
        return getPostDetails(page, floorArea);
    }

    private Page<PostDetails> getPostDetails(Pageable page, int floorArea) {
        List<Post> posts = postRepository.findAll(page).getContent();
        List<PostDetails> postDetail = new ArrayList<>();
        for (Post post : posts
        ) {
            if (post.getFloorArea() <= floorArea)
                postDetail.add(modelMapper.map(post, PostDetails.class));
        }

        return new PageImpl<PostDetails>(postDetail, page, page.getPageSize());
    }

    private Page<PostDetails> getPostDetails(Pageable page) {
        List<Post> posts = postRepository.findAll(page).getContent();
        List<PostDetails> postDetail = new ArrayList<>();
        for (Post post : posts
        ) {

            postDetail.add(modelMapper.map(post, PostDetails.class));
        }

        return new PageImpl<PostDetails>(postDetail, page, page.getPageSize());
    }

    private Page<PostDetails> getPostDetails(Pageable page, int mix, int max) {
        List<Post> posts = postRepository.findAll(page).getContent();
        List<PostDetails> postDetail = new ArrayList<>();
        for (Post post : posts
        ) {
            if (post.getPrice() < max && post.getPrice() >= mix)
                postDetail.add(modelMapper.map(post, PostDetails.class));
        }

        return new PageImpl<PostDetails>(postDetail, page, page.getPageSize());
    }


//    public void checkFormatPost(PostDetails postDetails) throws LatitudeException, LongitudeException {
//        if (!NumberUtils.isParsable("" + postDetails.getLatitude()))
//            throw new LatitudeException();
//        if (!NumberUtils.isParsable("" + postDetails.getLongitude()))
//            throw new LongitudeException();
//    }


}
