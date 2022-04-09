package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.CommentRepository;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.ICommentService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.exception.IdNotFound;
import io.tomcode.j4rent.mapper.CommentCreate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("commentService")
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final IAlbumService albumService;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, IAlbumService albumService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentCreate createComment(CommentCreate commentCreate) throws IdNotFound {
        Album album = albumService.getAlbumById(commentCreate.getId());
//        Post post = postService.getPostById(commentCreate.getId());
        Comment comment = commentRepository.findCommentById(commentCreate.getId());
        Comment create;
//        if (album != null || post != null || comment != null) {
        if (album != null || comment != null) {
            if (album != null)
                create = commentRepository.save(new Comment(album, commentCreate.getContents()));
            else
//            else if (post != null)
//                create = commentRepository.save(new Comment(post, commentCreate.getContents()));
//            else
                create = commentRepository.save((new Comment(comment, commentCreate.getContents())));
            return modelMapper.map(create, CommentCreate.class);
        } else throw new IdNotFound();
    }

    @Override
    public Page<CommentCreate> getComments(Pageable page, UUID uuid) throws IdNotFound {
        Album album = albumService.getAlbumById(uuid);
//        Post post = postService.getPostById(uuid);
        Comment comment = commentRepository.findCommentById(uuid);
        ArrayList<CommentCreate> results = new ArrayList<>();
        List<Comment> list;
//        if (album != null || post != null || comment != null) {
        if (album != null || comment != null) {
            if (album != null) {
                list = commentRepository.findAllByAlbum(album);
            }
//            else if (post != null)
//                list = commentRepository.findAllByPost(post);
            else
                list = commentRepository.findAllByParentN(comment);
            for (Comment cm : list
            ) {
                results.add(modelMapper.map(cm, CommentCreate.class));

            }
            return new PageImpl<CommentCreate>(results, page, page.getPageSize());
        } else throw new IdNotFound();
    }

    @Override
    public Long countComment(Post post) throws IdNotFound {
        long count;
        if (post != null) {
            count = commentRepository.countDistinctByPostEquals(post);
            if (post.getComments().size() > 0) {
                for (Comment comment : post.getComments()
                ) {
                    count += commentRepository.countDistinctByParentNEquals(comment);
                }
            }
            return count;
        } else
            throw new IdNotFound();
    }


}
