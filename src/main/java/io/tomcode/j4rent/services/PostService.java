package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.PostRepository;
import io.tomcode.j4rent.core.services.*;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.PostView;
import org.apache.commons.lang3.math.NumberUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("postService")
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private final IAlbumService albumService;
    private final IDocumentService documentService;
    private final IAccountService accountService;


    public PostService(PostRepository postRepository, ModelMapper modelMapper, IAlbumService albumService, IDocumentService documentService, IAccountService accountService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.documentService = documentService;
        this.accountService = accountService;
    }

    @Override
    public PostView createPost(PostCreate postCreate) throws ImageFailException, LatitudeException, LongitudeException, FloorAreaIncorrectValue, PriceIncorrectValue {
        checkFormatPost(postCreate);
        Album album = albumService.createAlbum(postCreate.getAlbum());
        Post post = new Post(postCreate);
        post.setAlbum(album);
        documentService.createDocument("post", postRepository.save(post));
        return modelMapper.map(post, PostView.class);

    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page) throws IdNotFound {
        List<Post> posts = postRepository.findAll(page).getContent();
        ArrayList<PostDetails> results = new ArrayList<>();
        for (Post post : posts) {
            results.add(convert(post));
        }
        return new PageImpl<>(results, page, page.getPageSize());

    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, int floorArea, int min, int max) throws IdNotFound {
        List<Post> posts = postRepository.findByFloorAreaLessThanEqualAndPriceBetween(floorArea, min, max, page);
        List<PostDetails> results = new ArrayList<>();
        for (Post post : posts
        ) {
            results.add(convert(post));

        }
        return new PageImpl<>(results, page, page.getPageSize());
    }


    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page) throws IdNotFound {
        Account account = accountService.getCurrentAccount();
        if (account == null)
            throw new IdNotFound();
        else {
            List<Post> posts = postRepository.findByCreatedByIdEquals(account.getId(), page);
            ArrayList<PostDetails> results = new ArrayList<>();
            for (Post post : posts) {
                results.add(convert(post));
            }
            return new PageImpl<>(results, page, page.getPageSize());
        }
    }


    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page, int floorArea, int min, int max) throws IdNotFound {
        Account account = accountService.getCurrentAccount();
        if (account == null)
            throw new IdNotFound();
        else {
            List<Post> posts = postRepository.findByCreatedByIdEqualsAndFloorAreaLessThanEqualAndPriceBetween(account.getId(), floorArea, Double.valueOf(min), Double.valueOf(max), page);
            ArrayList<PostDetails> results = new ArrayList<>();
            for (Post post : posts) {
                results.add(convert(post));
            }
            return new PageImpl<>(results, page, page.getPageSize());
        }
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findPostById(id);
    }


    public void checkFormatPost(PostCreate post) throws LatitudeException, LongitudeException, FloorAreaIncorrectValue, PriceIncorrectValue {
        if (!NumberUtils.isParsable(String.valueOf(post.getLatitude())))
            throw new LatitudeException();
        if (!NumberUtils.isParsable(String.valueOf(post.getLongitude())))
            throw new LongitudeException();
        if (post.getFloorArea() <= 0)
            throw new FloorAreaIncorrectValue();
        if (post.getPrice() <= 0)
            throw new PriceIncorrectValue();

    }

    public PostDetails convert(Post post) {
        int count = Math.toIntExact(postRepository.countCommentInPost(post));
        List<UUID> list = postRepository.findCommentInPost(post);
        for (UUID uuid : list) {
            count += sumComment(uuid);
        }
        PostDetails details = modelMapper.map(post, PostDetails.class);
        details.setSumComment(count);
        return details;
    }


    public int sumComment(UUID uuid) {
        int result = 0;
        List<UUID> list = postRepository.findComment(uuid);
        for (UUID child : list) {
            result++;
            List<UUID> childList = postRepository.findComment(child);
            if (childList.size() > 0) {
                result += sumComment(child);
            }
        }
        return result;
    }


}
