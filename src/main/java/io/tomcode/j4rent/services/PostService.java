package io.tomcode.j4rent.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.PostRepository;
import io.tomcode.j4rent.core.services.*;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service("postService")
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper mapper;
    private final IAlbumService albumService;
    private final IDocumentService documentService;
    private final IAccountService accountService;

    private final IRoleService roleService;


    public PostService(PostRepository postRepository, ModelMapper modelMapper, ObjectMapper mapper, IAlbumService albumService, IDocumentService documentService, IAccountService accountService, IRoleService roleService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.albumService = albumService;
        this.documentService = documentService;
        this.accountService = accountService;
        this.roleService = roleService;
    }

    @Override
    public PostView createPost(PostCreate postCreate) throws ImageFailException, LatitudeException, LongitudeException, FloorAreaIncorrectValue, PriceIncorrectValue, UserPostsNotFoundException, PermissionIsNoFound {
        checkFormatPost(postCreate);
        Account account = accountService.getCurrentAccount();
        if (account == null) throw new UserPostsNotFoundException();
        if (roleService.checkRolePermission(account.getId(), "create")) throw new PermissionIsNoFound();
        Album album = albumService.createAlbum(postCreate.getAlbum());
        Post post = new Post(postCreate);
        post.setAlbum(album);
        post.setCreatedById(account.getId());
        PostDetails postDetails= modelMapper.map(post, PostDetails.class);
        postDetails.setCreatedBy(modelMapper.map(account, UserInfo.class));
        documentService.createDocument("post", postDetails);
        return modelMapper.map(post, PostView.class);
    }

    @Override
    public PostView createPost(Post post) {
        postRepository.save(post);
        return modelMapper.map(post, PostView.class);
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page) {
        List<Post> posts = postRepository.findAll(page).getContent();
        ArrayList<PostDetails> results = new ArrayList<>();
        for (Post post : posts) {
            results.add(convert(post));
        }
        return new PageImpl<>(results, page, page.getPageSize());
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, float floorArea, BigInteger min, BigInteger max) {
        List<Post> posts = postRepository.findByFloorAreaLessThanEqualAndPriceBetween(floorArea, min, max, page);
        List<PostDetails> results = new ArrayList<>();
        for (Post post : posts) {
            results.add(convert(post));
        }
        return new PageImpl<>(results, page, page.getPageSize());
    }

    @Override
    public Page<PostDetails> getAllPost(Pageable page, float floorArea, BigInteger min, BigInteger max, double latitude, double longitude, double distance) {
        List<Post> posts = postRepository.findPostsByCoordinates(distance, latitude, longitude, floorArea, min, max, page);
        List<PostDetails> results = new ArrayList<>();
        if (posts.size() > 0) {
            for (Post post : posts) {
                results.add(convert(post));
            }
        } else {
            return getAllPost(page, floorArea, min, max);
        }
        return new PageImpl<>(results, page, page.getPageSize());
    }

    @Override
    public Page<PostDetails> getCreatedPosts(Pageable page) throws IdIsNotFoundException {
        Account account = accountService.getCurrentAccount();
        if (account == null)
            throw new IdIsNotFoundException();
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
    public Page<PostDetails> getCreatedPosts(Pageable page, float floorArea, BigInteger min, BigInteger max) throws IdIsNotFoundException {
        Account account = accountService.getCurrentAccount();
        if (account == null)
            throw new IdIsNotFoundException();
        else {
            List<Post> posts = postRepository.findByCreatedByIdEqualsAndFloorAreaLessThanEqualAndPriceBetween(account.getId(), floorArea, min, max, page);
            ArrayList<PostDetails> results = new ArrayList<>();
            for (Post post : posts) {
                results.add(convert(post));
            }
            return new PageImpl<>(results, page, page.getPageSize());
        }
    }

    @Override
    public PostDetails updatePost(PostUpdate post) throws FloorAreaIncorrectValue, PriceIncorrectValue, ImageFailException, UserPostsNotFoundException, PermissionIsNoFound {
        checkFormatPost(post);
        Account account = accountService.getCurrentAccount();
        Post updatePost = postRepository.findPostById(post.getId());
        if (!accountService.checkUserPermission(account.getId(), "update")) throw new PermissionIsNoFound();
        if (!account.getId().equals(updatePost.getCreatedById())) throw new UserPostsNotFoundException();
        updatePost.setContents(post.getContents());
        updatePost.setLatitude(post.getLatitude());
        updatePost.setLongitude(post.getLongitude());
        updatePost.setPrice(post.getPrice());
        updatePost.setFloorArea(post.getFloorArea());
        updatePost.setFurnitureStatus(post.getFurnitureStatus());
        if (post.getAlbum() != null) {
            Album updateAlbum = albumService.getAlbumById(updatePost.getAlbum().getId());
            if (updateAlbum != null) {
                albumService.updateAlbum(new AlbumUpdate(updateAlbum, post.getAlbum()));
            } else
                albumService.createAlbum(post.getAlbum());
        }
        return modelMapper.map(updatePost, PostDetails.class);
    }

    @Override
    public void deletePost(UUID uuid) throws PermissionIsNoFound, IdUserIsNotFoundException, UserPostsNotFoundException {
        Account account = accountService.getCurrentAccount();
        Post post = postRepository.findPostById(uuid);
        if (roleService.checkRolePermission(account.getId(), "delete")) throw new PermissionIsNoFound();
        if (account == null) throw new IdUserIsNotFoundException();
        if (account.getId() != post.getCreatedById()) throw new UserPostsNotFoundException();
        postRepository.delete(post);

    }

    @Override
    public PostDetails createPostFromDocument(UUID uuid) throws DocumentIsNotFoundException, ImageFailException {
            Document document = documentService.getDocument(uuid, "post");
            if (document == null) throw new DocumentIsNotFoundException();
            PostDetails post = mapper.convertValue(document.getData(), PostDetails.class);
            Post createPost = new Post(post);
            createPost.setAlbum(albumService.createAlbum(post.getAlbum()));
            createPost(createPost);
            return post;
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
        if (post.getPrice().compareTo(new BigInteger("0")) <= 0)
            throw new PriceIncorrectValue();

    }

    public void checkFormatPost(PostUpdate post) throws FloorAreaIncorrectValue, PriceIncorrectValue {
        if (post.getFloorArea() <= 0)
            throw new FloorAreaIncorrectValue();
        if (post.getPrice().compareTo(new BigInteger("0")) <= 0)
            throw new PriceIncorrectValue();

    }

    public PostDetails convert(Post post) {
        int count = Math.toIntExact(postRepository.countCommentInPost(post));
        List<UUID> list = postRepository.findCommentInPost(post);
        for (UUID uuid : list) {
            count += sumComment(uuid);
        }
        PostDetails details = modelMapper.map(post, PostDetails.class);
        details.setCreatedBy(modelMapper.map(accountService.getAccountById(post.getCreatedById()), UserInfo.class));
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
