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
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("commentService")
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final IPostService postService;
    private final IAlbumService albumService;

    public CommentService(CommentRepository commentRepository, IPostService postService, IAlbumService albumService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.albumService = albumService;
    }



    @Override
    public Comment createComment(CommentCreate commentCreate) throws IdNotFound {
        Album album = albumService.getAlbumById(commentCreate.getId());
        Post post = postService.getPostById(commentCreate.getId());
        Comment comment = commentRepository.findCommentById(commentCreate.getId());
        if (album!=null || post!=null|| comment!= null) {
            if (album!=null){
               return commentRepository.save(new Comment(album, commentCreate.getContent()));
            }else if (post!=null)
                return commentRepository.save(new Comment(post, commentCreate.getContent()));
            else
                return commentRepository.save((new Comment(comment,commentCreate.getContent())));
        }
        else throw new IdNotFound();
    }

}
