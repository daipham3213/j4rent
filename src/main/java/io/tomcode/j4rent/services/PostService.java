package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.PostRepository;
import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.IDocumentService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.PostDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service("postService")
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private final IAlbumService albumService;
    private final IDocumentService documentService;
    private final IAccountService accountService;

    public PostService(PostRepository postRepository, IDocumentService documentService, ModelMapper modelMapper, AlbumService albumService, IAccountService accountService) {
        this.postRepository = postRepository;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.accountService = accountService;
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
        return null;
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, int min, int max) {
        return null;
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, int floorArea) {
        return null;
    }

    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page) {
        Account account = accountService.getCurrentAccount();
        List<Post> posts = postRepository.findByCreatedByIdEquals(account.getId(), page);
        ArrayList<PostDetails> results = new ArrayList<>();
        for (Post post: posts) {
            results.add(modelMapper.map(post, PostDetails.class));
        }
        return new PageImpl<>(results, page, page.getPageSize());
    }

    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page, int min, int max) {
        return null;
    }

    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page, int floorArea) {
        return null;
    }
}
